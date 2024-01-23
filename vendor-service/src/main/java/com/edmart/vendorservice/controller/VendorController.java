package com.edmart.vendorservice.controller;


import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.product.ProductServiceClient;
import com.edmart.client.vendor.VendorRecord;
import com.edmart.client.vendor.VendorResponse;
import com.edmart.vendorservice.exception.VendorCreationSuccessException;
import com.edmart.vendorservice.service.VendorServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {

    private final VendorServiceImpl vendorService;


    //private final ProductServiceClient feignClient;

    private final KafkaTemplate<String, VendorRecord> kafkaTemplate;

    private final static int PAGENO = 0;
    private final static int PAGESIZE = 10;

    private final static String SORTBY = "vendorId";

    private final static String SORTDIR = "asc";

    @GetMapping
    public ResponseEntity<VendorResponse> getAllVendors(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "vendorId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        VendorResponse vendors = vendorService.getAllVendors(pageNo, pageSize, sortBy, sortDir);
        log.info("List of vendors retrieved successfully");
        return ResponseEntity.ok().body(vendors);
    }

    @GetMapping("/page")
    public ResponseEntity<VendorResponse> getVendorsByPage(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "vendorName") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir
    ) {
        try {
            VendorResponse vendorResponse = vendorService.getVendorsByPage(pageNo, pageSize, sortBy, sortDir);
            return new ResponseEntity<>(vendorResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{vendorId}")
    public EntityModel<VendorRecord> getVendorById(@PathVariable Long vendorId) throws VendorNotFoundException, ProductNotFoundException {
        EntityModel<VendorRecord> resource = EntityModel.of(vendorService.getVendorById(vendorId));
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllVendors(PAGENO, PAGESIZE, SORTBY, SORTDIR))
                .withRel("get_all_available_vendors"));
//        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllProducts(vendorId))
//                .withRel("get_all_available_products"));

        log.info("Vendor retrieved successfully: {}", resource);

        return resource;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<VendorRecord>> getVendorByVendorName(@RequestParam("name") String vendorName) throws VendorNotFoundException {
        Optional<VendorRecord> vendor = vendorService.getVendorByName(vendorName);
        log.info("Vendor retrieved successfully: {}", vendor);
        return ResponseEntity.ok().body(vendor);
    }

//    @GetMapping("/products/{vendorId}")
//    public ResponseEntity<Optional<List<ProductDTO>>> getAllProducts(@PathVariable("vendorId") Long vendorId) throws ProductNotFoundException {
//        log.info("retrieving all available products");
//
//        return feignClient.getAllItemsByVendorId(vendorId);
//    }

    @PostMapping
    public ResponseEntity<String> createVendor(@RequestBody VendorRecord vendorRecord) throws VendorNotFoundException, VendorCreationSuccessException {
       // kafkaTemplate.send("vendor-topic", "string", vendorRecord);
        vendorService.createNewVendor(vendorRecord);
        log.info("Vendor created successfully: {}", vendorRecord);
        return ResponseEntity.ok("Vendor created successfully!!");
    }

    @PutMapping("/{vendorId}")
    public ResponseEntity<String> updateVendor(@PathVariable Long vendorId,
                                               @RequestBody VendorRecord vendorRecord) throws VendorNotFoundException {
        log.info("Updating record with Id: {}", vendorId);
        vendorService.updateVendor(vendorId, vendorRecord);

        return ResponseEntity.ok("Vendor updated successfully");
    }

    @DeleteMapping("/{vendorId}")
    public ResponseEntity<String> deleteVendorById(@PathVariable Long vendorId) throws VendorNotFoundException {
        vendorService.deleteVendorById(vendorId);
        log.info("Vendor deleted successfully, Vendor ID: {}", vendorId);
        return ResponseEntity.ok("Vendor deleted successfully!!");
    }
}