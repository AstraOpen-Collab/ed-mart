package com.edmart.product.service;

import com.edmart.product.dto.ProductDTO;
import com.edmart.product.dto.ProductResponseDTO;
import com.edmart.product.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    void createProduct(ProductDTO productDTO) throws ProductNotFoundException;

    ProductResponseDTO getAllProducts(int page, int size, String sortBy, String sortDir);

    ProductDTO getProduct(Long productId) throws ProductNotFoundException;

    void updateProduct(Long productId, ProductDTO productDTO) throws ProductNotFoundException;

    void deleteProduct(Long productId) throws ProductNotFoundException;

}
