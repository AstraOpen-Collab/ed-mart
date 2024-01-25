package com.edmart.vendorservice.service;

import com.edmart.client.product.ProductDTO;
import com.edmart.client.product.ProductServiceClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class VendorManagementServiceImpl implements VendorManagementService{


//    private final ProductServiceClient productServiceClient;
//
//    @Override
//    public Optional<List<ProductDTO>> getAllVendorProductsByVendorId(Long vendorId) {
//        return Optional.of(productServiceClient.getAllProductsByVendorId(vendorId).getBody().get());
//    }

    @Override
    public Optional<List<ProductDTO>> getAllVendorProductsByVendorId(Long vendorId) {
        return Optional.empty();
    }

    @Override
    public void createVendorProduct(Long vendorId, ProductDTO productDTO) {

    }

    @Override
    public void deleteVendorProductById(Long vendorId, Long productId) {

    }

    @Override
    public void updateVendorProduct(Long vendorId, ProductDTO productDTO) {

    }
}
