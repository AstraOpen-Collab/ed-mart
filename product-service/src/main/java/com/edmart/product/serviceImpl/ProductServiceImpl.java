package com.edmart.product.serviceImpl;

import com.edmart.client.category.CategoryClient;
import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.product.ProductDTO;
import com.edmart.client.product.ProductResponseDTO;
import com.edmart.client.product.ProductStatus;
import com.edmart.client.vendor.VendorClient;
import com.edmart.contracts.product.InventorySchema;
import com.edmart.contracts.product.ProductDeleteEvent;
import com.edmart.product.mappers.ProductMapper;
import com.edmart.product.model.Product;
import com.edmart.product.repository.ProductRepository;
import com.edmart.product.service.ProductService;
import com.edmart.product.utils.Pagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryClient categoryClient;

    private final VendorClient vendorClient;

    private final ProductMapper productMapper;

    private static final Long DEFAULT_CATEGORY_ID = 0L;
    private final NewTopic topic;
    private final KafkaTemplate<String, InventorySchema> kafkaTemplate;


    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void createProduct(ProductDTO productDTO) throws ProductNotFoundException {
        Product product = setProductProperties(productDTO);
        try{
            productRepository.save(product);

            String itemStatus = productDTO.status().toString();

            InventorySchema message = new InventorySchema(
                    product.getProductId(),
                    productDTO.quantity(),
                    productDTO.status().getClass().getName()
            );
            SendMessageToProductInventoryTopic(message);
        }catch (Exception e){
            log.error("Error creating product with name {}, caused by {}",productDTO.name(),e.getCause());
        }

    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void vendorCreateProduct(Long vendorId, ProductDTO productDTO) throws ProductNotFoundException {
        Product product = setProductProperties(productDTO);

        try{
            if (!vendorClient.getVendorById(vendorId).getStatusCode().is2xxSuccessful()) {
                throw new VendorNotFoundException("Vendor does not exist in the system!");
            }

            product.setVendorId(vendorId);
            log.info("++++++++++++READING ENUM VALUE: {}", ProductStatus.valueOf(productDTO.status().toString()));

            productRepository.save(product);

            //Sending message to productInventoryTopic
            log.info("++++++++++++READING ENUM VALUE: {}", ProductStatus.valueOf(productDTO.status().toString()));

            SendMessageToProductInventoryTopic(
                    this.setInventorySchema(
                            product.getProductId(),
                            productDTO.quantity(),
                            productDTO.status().toString()

                    )
            );
        }catch (Exception e){
            log.error("Error creating product with vendorId: {}, caused by {}", vendorId, e.getMessage().toString());
        }

    }

    public InventorySchema setInventorySchema(long productId, int quantity, String status){
        return new InventorySchema(productId, quantity, status);
    }

    public Product setProductProperties(ProductDTO productDTO){

        return Product.builder()
                .categoryId(productCategory(productDTO.categoryId()))
                .description(productDTO.description())
                .name(productDTO.name())
                .SKU(productDTO.SKU())
                .units(productDTO.units())
                .quantity(productDTO.quantity())
                .prices(productDTO.prices())
                .measurements(productDTO.measurements())
                .image(productDTO.image())
                .rating(productDTO.rating())
                .vendorId(productDTO.vendorId())
                .status(productDTO.status())
                .build();
    }

    //Method to check existence of a category from the category service
    public Long productCategory(Long id){
        if(!categoryClient.checkCategoryById(id).getStatusCode().is2xxSuccessful()){
            log.info("Using default Category..");
            return DEFAULT_CATEGORY_ID;
        }
        log.info("Category check status is positive!");
        return id;
    }

    @Override
    @Cacheable(cacheNames = "products")
    public ProductResponseDTO getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductDTO> productPage = productRepository.findAll(pageable).map(productMapper);

        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setProductList(productPage.getContent());
        productResponseDTO.setPageInfoDTO(Pagination.getPageInfoDTO(productPage));

        return  productResponseDTO;
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#productId")
    public ProductDTO getProduct(Long productId) throws ProductNotFoundException {
        Optional<ProductDTO> productDTOOptional = productRepository.findById(productId)
                .map(productMapper);
        if(productDTOOptional.isPresent()){
            return productDTOOptional.get();
        }else{
            throw new ProductNotFoundException("No Product exist with this Id");
        }
    }

    @Override
    @Cacheable(cacheNames = "products", key = "#vendorId")
    public Optional<List<ProductDTO>> getAllProductsByVendorId(Long vendorId) throws VendorNotFoundException {
        return Optional.of(new ArrayList<>(productRepository
                .findProductsByVendorId(vendorId).get()));
    }

    @Override
    public ProductResponseDTO getProductsByVendorId(int page, int size, String sortBy, String sortDir, Long vendorId) throws VendorNotFoundException {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductDTO> productPage = productRepository.findAllByVendorId(vendorId, pageable).map(productMapper);

        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setProductList(productPage.getContent());
        productResponseDTO.setPageInfoDTO(Pagination.getPageInfoDTO(productPage));

        return  productResponseDTO;
    }

    @Override
    @CachePut(cacheNames = "products", key = "#productId")
    public void updateProduct(Long productId, ProductDTO productDTO) throws ProductNotFoundException {
        try{
            Product product = productRepository.findById(productId)
                    .orElseThrow(()->new ProductNotFoundException("Product does not exist"));
            log.info("updating product with name: {}", product.getName());

            BeanUtils.copyProperties(productDTO, product, getNullPropertyNames(productDTO));

            productRepository.save(product);
        }catch(Exception ex){
            log.error("Error updating product with name {} caused by {}", productDTO.name(), ex.getMessage());
        }
    }

    @Override
    @CachePut(cacheNames = "products", key = "#productId")
    public void updateVendorProduct(Long vendorId, Long productId, ProductDTO productDTO)
            throws VendorNotFoundException, ProductNotFoundException{
        try{
            Product product = productRepository.findProductsByVendorIdAndProductId(vendorId, productId)
                    .orElseThrow(()->new ProductNotFoundException("Product does not exist"));
            log.info("updating product with name: {}", product.getName());

            BeanUtils.copyProperties(productDTO, product, getNullPropertyNames(productDTO));



            if(productDTO.quantity()==null){
                productRepository.save(product);
            }else{
                productRepository.save(product);

                SendMessageToProductInventoryTopic(
                        this.setInventorySchema(
                                product.getProductId(),
                                productDTO.quantity(),
                                productDTO.status().toString()
                        )
                );
            }

        }catch(Exception ex){
            log.error("Error updating product with name {} caused by {}", productDTO.name(), ex.getMessage());
        }
    }

    @Override
    @CacheEvict(cacheNames = "products", key = "#productId", beforeInvocation = true)
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<ProductDTO> productDTOOptional = productRepository.findById(productId).map(productMapper);

        var productDeleteEvent = new ProductDeleteEvent(productId, productDTOOptional.get().name());

        productRepository.deleteById(productId);
        sendMessageToProductDeleteTopic(productDeleteEvent);
    }

    @Override
    @CacheEvict(cacheNames = "products", key = "#productId", beforeInvocation = true)
    public void deleteProductByVendorIdAndProductId(Long vendorId, Long productId) throws VendorNotFoundException {
        Optional<ProductDTO> productDTOOptional = productRepository
                .findProductsByVendorIdAndProductId(vendorId, productId).map(productMapper);

        var productDeleteEvent = new ProductDeleteEvent(productId, productDTOOptional.get().name());

        if(productDTOOptional.isPresent()){
            productRepository.deleteById(productId);

            sendMessageToProductDeleteTopic(productDeleteEvent);
        }else{
            throw new ProductNotFoundException("Product with this vendorId and productId does not exist");
        }
    }

    private String[] getNullPropertyNames(ProductDTO productDTO) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(productDTO);
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



//    @KafkaListener(
//            topics = "category-topic",
//            groupId = "${spring.kafka.consumer.group-id}"
//    )
//    public Boolean consumeCategoryCheck(Boolean variable){
//        return variable.equals(true);
//    }
//
//    public void sendMessage(Long categoryId){
//        Message<Long> message = MessageBuilder
//                .withPayload(categoryId)
//                .setHeader(KafkaHeaders.TOPIC, topic.name())
//                .build();
//
//        kafkaTemplate.send(message);
//    }

    public void SendMessageToProductInventoryTopic(InventorySchema inventorySchemaEvent) {
        log.info("Sending new Inventory event data => : {}", inventorySchemaEvent);

        Message<InventorySchema> payload = MessageBuilder
                .withPayload(inventorySchemaEvent)
                .setHeader(KafkaHeaders.TOPIC,"product_inventory_topic")
                .setHeader(KafkaHeaders.MESSAGE_KEY, generateShortUUID())
                .build();

        kafkaTemplate.send(payload);
    }

    public void sendMessageToProductDeleteTopic(ProductDeleteEvent productDeleteEvent) {
        log.info("Sending new Product Delete event data => : {}", productDeleteEvent);

        Message<ProductDeleteEvent> payload = MessageBuilder
                .withPayload(productDeleteEvent)
                .setHeader(KafkaHeaders.TOPIC, "product_delete_topic")
                .setHeader(KafkaHeaders.MESSAGE_KEY, generateShortUUID())
                .build();

        kafkaTemplate.send(payload);
    }

    public static String generateShortUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        return uuid;
    }

}
