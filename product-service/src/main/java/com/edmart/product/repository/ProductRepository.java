package com.edmart.product.repository;

import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.product.ProductDTO;
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

}
