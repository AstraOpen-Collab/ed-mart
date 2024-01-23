package com.edmart.client.product;

import com.edmart.client.exceptions.ProductNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        value = "product",
        path = "/api/v1/products"
)
public interface ProductServiceClient {

    @GetMapping
    ResponseEntity<ProductResponseDTO> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                             @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir);

    @GetMapping("/{Id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("Id") Long Id) throws ProductNotFoundException;
//    @GetMapping("/vendors/{vendorId}")
//    ResponseEntity<Optional<List<ProductRequest>>> getAllItemsByVendorId(@PathVariable("vendorId") Long vendorId) throws ProductNotFoundException;

}
