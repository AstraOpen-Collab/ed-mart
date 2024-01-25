package com.edmart.client.product;

import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.exceptions.VendorNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@FeignClient(
        value = "product-service",
        path = "/api/v1/products"
)
public interface ProductServiceClient {

    @GetMapping
    ResponseEntity<ProductDTO> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "5") int size,
                                                      @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir);

    @GetMapping("/{Id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("Id") Long Id) throws ProductNotFoundException;

    @GetMapping("/vendors/{vendorId}")
    ResponseEntity<Optional<List<ProductDTO>>> getAllProductsByVendorId(@PathVariable("vendorId") Long vendorId) throws VendorNotFoundException;

    @PostMapping("/vendor/{vendorId}")
    ResponseEntity<String> vendorCreateProduct(@PathVariable("vendorId") Long vendorId, @RequestBody ProductDTO productDTO) throws ProductNotFoundException;

}