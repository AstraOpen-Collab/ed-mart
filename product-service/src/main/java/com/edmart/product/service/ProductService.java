package com.edmart.product.service;

import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.product.ProductDTO;
import com.edmart.client.product.ProductResponseDTO;
import com.edmart.client.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    void createProduct(ProductDTO productDTO) throws ProductNotFoundException;

    void vendorCreateProduct(Long vendorId, ProductDTO productDTO) throws ProductNotFoundException;

    ProductResponseDTO getAllProducts(int page, int size, String sortBy, String sortDir);

    ProductDTO getProduct(Long productId) throws ProductNotFoundException;

    Optional<List<ProductDTO>> getAllProductsByVendorId(Long vendorId) throws VendorNotFoundException;

    ProductResponseDTO getProductsByVendorId(int page, int size, String sortBy, String sortDir, Long vendorId) throws VendorNotFoundException;

    void updateProduct(Long productId, ProductDTO productDTO) throws ProductNotFoundException;

    void updateVendorProduct(Long vendorId, Long productId, ProductDTO productDTO) throws VendorNotFoundException, ProductNotFoundException;

    void deleteProduct(Long productId) throws ProductNotFoundException;

    void deleteProductByVendorIdAndProductId(Long vendorId, Long productId) throws VendorNotFoundException;



}
