package com.edmart.product.controller;

import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.product.ProductDTO;
import com.edmart.client.product.ProductResponseDTO;
import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductResponseDTO> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @RequestParam(value = "sortBy", defaultValue = "createdAt", required = false) String sortBy,
                                                             @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir){
        log.info("Retrieving all products: {}", productService.getAllProducts(page, size,sortBy, sortDir));

        return ResponseEntity.ok().body(productService.getAllProducts(page, size,sortBy, sortDir));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO productDTO) throws ProductNotFoundException {
        log.info("Creating a product with name: {}",productDTO.name());

        productService.createProduct(productDTO);

        return ResponseEntity.ok().body("Product created successfully");
    }


    @PostMapping("/vendor/{vendorId}")
    public ResponseEntity<String> vendorCreateProduct(@PathVariable("vendorId") Long vendorId, @RequestBody ProductDTO productDTO) throws ProductNotFoundException {
        log.info("Creating a product with name: {} by vendor with Id: {}",productDTO.name(), vendorId);

        productService.vendorCreateProduct(vendorId, productDTO);

        return ResponseEntity.ok().body("Product created successfully");
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("Id") Long Id) throws ProductNotFoundException {
        log.info("Retrieving a product with Id: {}", Id);

        return ResponseEntity.ok().body(productService.getProduct(Id));
    }

    @GetMapping("/vendors/{vendorId}")
    public ResponseEntity<Optional<List<ProductDTO>>> getAllProductsByVendorId(@PathVariable("vendorId") Long vendorId) throws VendorNotFoundException {
        log.info("Retrieving all items with vendor Id: {}", vendorId);
        return ResponseEntity.ok().body(productService.getAllProductsByVendorId(vendorId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO request, @PathVariable Long productId) throws ProductNotFoundException {
        log.info("Updating a product with name:{}",request.name());

        productService.updateProduct(productId, request);

        log.info("Updated Product Successfully {}", request);

        return ResponseEntity.ok().body("Updated Successfully..");
    }

    @PutMapping("/{productId}/vendors/{vendorId}")
    public ResponseEntity<String> updateVendorProduct(@RequestBody ProductDTO request, @PathVariable("vendorId") Long vendorId, @PathVariable("productId") Long productId)
            throws ProductNotFoundException, VendorNotFoundException {

        log.info("Updating product with productId : {} and vendorId : {}", productId, vendorId);

        productService.updateVendorProduct(vendorId, productId, request);

        log.info("Updated Product Successfully {}", request);

        return ResponseEntity.ok().body("Updated Successfully..");
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);

        log.info("Successfully deleted product with Id {}", productId);

        return ResponseEntity.ok("Product deleted successfully!");
    }

    @DeleteMapping("/{productId}/vendors/{vendorId}")
    public ResponseEntity<String> deleteVendorProduct(@PathVariable("vendorId") Long vendorId, @PathVariable("productId") Long productId)
            throws ProductNotFoundException, VendorNotFoundException {

        productService.deleteProductByVendorIdAndProductId(vendorId, productId);

        log.info("Successfully deleted product with Id {}", productId);

        return ResponseEntity.ok("Product deleted successfully!");
    }
}
