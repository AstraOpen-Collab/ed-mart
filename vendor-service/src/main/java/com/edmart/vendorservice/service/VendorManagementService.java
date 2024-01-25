package com.edmart.vendorservice.service;

import com.edmart.client.product.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface VendorManagementService {

    Optional<List<ProductDTO>> getAllVendorProductsByVendorId(Long vendorId);

    void createVendorProduct(Long vendorId, ProductDTO productDTO);

    void deleteVendorProductById(Long vendorId, Long productId);

    void updateVendorProduct(Long vendorId, ProductDTO productDTO);

}
