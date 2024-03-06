package com.edmart.client.product;

import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.exceptions.VendorNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(
        value = "product-service",
        path = "/api/v1/products"
)
public interface ProductServiceClient {

    @GetMapping
    ResponseEntity<ProductResponseDTO> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "5") int size,
                                                      @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir);

    @GetMapping("/{Id}")
    ResponseEntity<ProductDTO> getProductById(@PathVariable("Id") Long Id) throws ProductNotFoundException;

//    @GetMapping("/vendors/{vendorId}")
//    ResponseEntity<Optional<List<ProductDTO>>> getAllProductsByVendorId(@PathVariable("vendorId") Long vendorId) throws VendorNotFoundException;

    @GetMapping("/vendors/{vendorId}")
    ResponseEntity<ProductResponseDTO> getAllVendorProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "5") int size,
                                                           @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                           @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir,
                                                           @PathVariable("vendorId") Long vendorId) throws VendorNotFoundException;

    @PostMapping("/vendor/{vendorId}")
    ResponseEntity<String> vendorCreateProduct(@PathVariable("vendorId") Long vendorId, @RequestBody ProductDTO productDTO) throws ProductNotFoundException;

    @PutMapping("/{productId}")
    ResponseEntity<String> updateProduct(@RequestBody ProductDTO request, @PathVariable("productId") Long productId) throws ProductNotFoundException;

    @PutMapping("/{productId}/vendors/{vendorId}")
    ResponseEntity<String> updateVendorProduct(@RequestBody ProductDTO request, @PathVariable("vendorId") Long vendorId, @PathVariable("productId") Long productId)
            throws ProductNotFoundException, VendorNotFoundException;

    @DeleteMapping("/{productId}")
    ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) throws ProductNotFoundException;

    @DeleteMapping("/{productId}/vendors/{vendorId}")
    ResponseEntity<String> deleteVendorProduct(@PathVariable("vendorId") Long vendorId, @PathVariable("productId") Long productId)
            throws ProductNotFoundException, VendorNotFoundException;
}