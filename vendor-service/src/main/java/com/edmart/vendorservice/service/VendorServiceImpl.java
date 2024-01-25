package com.edmart.vendorservice.service;



import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.vendor.*;
import com.edmart.vendorservice.Dto.VendorDtoMapper;
import com.edmart.vendorservice.entity.Vendor;
import com.edmart.vendorservice.repository.VendorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class VendorServiceImpl implements VendorService{

    private final static Logger LOGGER = LoggerFactory.getLogger(VendorServiceImpl.class);
    private final VendorRepository vendorRepository;


    private final VendorDtoMapper vendorDtoMapper;

    private final static int PAGE_SIZE = 5;

    private final NewTopic topic;
    private final KafkaTemplate<String, VendorRecord> kafkaTemplate;

    public void sendMessage(VendorRecord vendorRecord){
        LOGGER.info("New VendorRecord event sent => {}", vendorRecord);
        Message<VendorRecord> message = MessageBuilder
                .withPayload(vendorRecord)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }

    /**
     * Retrieves all vendors.
     *
     * @return List of VendorRecord objects.
     */
    @Override
    @Cacheable(value = "vendors")
    public VendorResponse getAllVendors(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Vendor> vendorRecordPage = vendorRepository.findAll(pageable);

        List<VendorRecord> listOfVendors =  vendorRecordPage.getContent().stream().map(vendorDtoMapper).toList();
        VendorResponse vendorResponse = new VendorResponse();
        vendorResponse.setContent(listOfVendors);
        vendorResponse.setPageNo(vendorRecordPage.getNumber());
        vendorResponse.setPageSize(vendorRecordPage.getSize());
        vendorResponse.setTotalElements(vendorRecordPage.getTotalElements());
        vendorResponse.setTotalPages(vendorRecordPage.getTotalPages());
        vendorResponse.setLast(vendorRecordPage.isLast());

        return vendorResponse;
    }

    @Cacheable(cacheNames = "vendors", key = "#pageNo")
    public VendorResponse getVendorsByPage(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Vendor> vendorRecordPage = vendorRepository.findAll(pageable);

        List<VendorRecord> listOfVendors = vendorRecordPage.getContent().stream().map(vendorDtoMapper).toList();

        return new VendorResponse(
                listOfVendors,
                vendorRecordPage.getNumber(),
                vendorRecordPage.getSize(),
                vendorRecordPage.getTotalElements(),
                vendorRecordPage.getTotalPages(),
                vendorRecordPage.isLast()
        );
    }

    /**
     * Retrieves a vendor by ID.
     *
     * @param vendorId The ID of the vendor to retrieve.
     * @return Optional VendorRecord object.
     * @throws VendorNotFoundException if the vendor is not found.
     */
    @Override
    @Cacheable(cacheNames = "vendors", key = "#vendorId")
    public VendorRecord getVendorById(Long vendorId) throws VendorNotFoundException {
        return vendorRepository.findById(vendorId)
                .map(vendorDtoMapper)
                .orElseThrow(()->new VendorNotFoundException("No such vendor exist with this Id" +vendorId));
    }


    /**
     * Retrieves a vendor by name.
     *
     * @param vendorName The name of the vendor to retrieve.
     * @return Optional VendorRecord object.
     * @throws VendorNotFoundException if the vendor is not found.
     */
    @Override
    @Cacheable(cacheNames = "vendors", key = "#vendorName")
    public Optional<VendorRecord> getVendorByName(String vendorName) throws VendorNotFoundException {
                try {

                    Optional<VendorRecord> vendor = Optional.ofNullable(vendorRepository.findByVendorNameIgnoreCase(vendorName)
                            .map(vendorDtoMapper)
                            .orElseThrow(() -> new VendorNotFoundException("No vendor is registered with this name!")));
                    return Optional.of(vendor.get());
                } catch (Exception e) {
                    log.error("Error getting vendor with Id: " + vendorName, e);
                    throw new VendorNotFoundException("Error getting vendor with Id: " + vendorName);
                }
            }

    /**
     * Creates a new vendor.
     *
     * @param vendorRecord The VendorRecord object representing the new vendor.
     * @throws VendorNotFoundException if the vendor is not found.
     */
    @Override
    @CacheEvict(value = "vendors", allEntries = true)
    public void createNewVendor(VendorRecord vendorRecord) throws VendorNotFoundException {

        try{
            Address vendorAddress = vendorRecord.vendorAddress();
            Contact vendorContact = vendorRecord.vendorContact();
            PurchaseInformation purchaseInfos = vendorRecord.purchaseInfos();

            Vendor vendor = Vendor.builder()
                    .vendorName(vendorRecord.vendorName())
                    .vendorAddress(vendorAddress)
                    .vendorContact(vendorContact)
                    .purchaseInfos(purchaseInfos)
                    .image(vendorRecord.image())
                    .build();

            //send a message to the vendor-topic of the kafka broker
            sendMessage(vendorRecord);

            vendorRepository.save(vendor);
        }catch (Exception e){
            log.error("Error creating new vendor: " + vendorRecord.vendorName(), e);
            throw new VendorNotFoundException("Error creating vendor with name: " + vendorRecord.vendorName());
        }

    }

    /**
     * Deletes a vendor by ID.
     *
     * @param vendorId The ID of the vendor to delete.
     * @throws VendorNotFoundException if the vendor is not found.
     */
    @Override
    @CacheEvict(cacheNames = "vendors", key = "#vendorId", beforeInvocation = true)
    public void deleteVendorById(Long vendorId) throws VendorNotFoundException {

        if(vendorRepository.existsById(vendorId)){
            vendorRepository.deleteById(vendorId);
        }else{
            throw new VendorNotFoundException("Vendor with this Id is not in the system");
        }
    }

    /**
     * Updates a vendor by ID.
     *
     * @param vendorId     The ID of the vendor to update.
     * @param vendorRecord The VendorRecord object representing the updated vendor.
     * @throws VendorNotFoundException if the vendor is not found.
     */
    @Override
    @CachePut(cacheNames = "vendors", key = "#vendorId")
    public void updateVendor(Long vendorId, VendorRecord vendorRecord) throws VendorNotFoundException {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new VendorNotFoundException("Vendor with this Id does not exist"));

        // Copy non-null properties from vendorRecord to vendor
        BeanUtils.copyProperties(vendorRecord, vendor, getNullPropertyNames(vendorRecord));

        vendorRepository.save(vendor);
    }

    private String[] getNullPropertyNames(VendorRecord vendorRecord) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(vendorRecord);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullProperties.add(propertyName);
            }
        }

        return nullProperties.toArray(new String[0]);
    }
}
