package com.edmart.product.repository;

import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.product.ProductDTO;
import com.edmart.client.product.ProductResponseDTO;
import com.edmart.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findAll(Pageable pageable);

    Optional<List<ProductDTO>> findProductsByVendorId(Long vendorId) throws VendorNotFoundException;

    List<Product> findAllByCategoryId(Long categoryId);

    Page<Product> findAllByVendorId(Long vendorId, Pageable pageable) throws VendorNotFoundException;


    void deleteProductByVendorIdAndAndProductId(Long vendorId, Long productId);

    Optional<Product> findProductsByVendorIdAndProductId(Long vendorId, Long productId) throws VendorNotFoundException, ProductNotFoundException;


}
