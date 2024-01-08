package com.edmart.product.serviceImpl;

import com.edmart.product.dto.ProductDTO;
import com.edmart.product.dto.ProductResponseDTO;
import com.edmart.product.exception.ProductNotFoundException;
import com.edmart.product.mappers.ProductMapper;
import com.edmart.product.model.Product;
import com.edmart.product.repository.ProductRepository;
import com.edmart.product.service.ProductService;
import com.edmart.product.utils.Pagination;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public void createProduct(ProductDTO productDTO) throws ProductNotFoundException {
        Product product = new Product();

        try{
            product.setProductId(productDTO.productId());
            product.setCategoryId(productDTO.categoryId());
            product.setDescription(productDTO.description());
            product.setImage(productDTO.image());
            product.setPrice(productDTO.price());
            product.setInventoryId(productDTO.inventoryId());
            product.setName(productDTO.name());
            product.setNewPrice(product.getNewPrice());
            product.setOldPrice(productDTO.oldPrice());
            product.setSKU(productDTO.SKU());

            productRepository.save(product);
        }catch (Exception e){
            log.error("Error creating product with name {}, caused by {}",productDTO.name(),e.getCause());
        }

    }

    @Override
    public ProductResponseDTO getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage = productRepository.findAll(pageable);

        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setProductList(productPage.getContent());
        productResponseDTO.setPageInfoDTO(Pagination.getPageInfoDTO(productPage));

        return  productResponseDTO;
    }

    @Override
    public ProductDTO getProduct(Long productId) throws ProductNotFoundException {
        Optional<ProductDTO> productDTOOptional = productRepository.findById(productId)
                .map(productMapper);
        if(productDTOOptional.isPresent()){
            return productDTOOptional.get();
        }else{
            throw new ProductNotFoundException("No Product exist with this Id");
        }
    }

    @Override
    public void updateProduct(Long productId, ProductDTO productDTO) throws ProductNotFoundException {
        try{
            Product product = productRepository.findById(productId)
                    .orElseThrow(()->new ProductNotFoundException("Product does not exist"));

            BeanUtils.copyProperties(productDTO, product, getNullPropertyNames(productDTO));

            productRepository.save(product);
        }catch(Exception ex){
            log.error("Error updating product with name {} caused by {}", productDTO.name(), ex.getMessage());
        }
    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Optional<ProductDTO> productDTOOptional = productRepository.findById(productId).map(productMapper);

        if(productDTOOptional.isPresent()){
            productRepository.deleteById(productId);
        }else{
            throw new ProductNotFoundException("Product with this Id does not exist");
        }
    }

    private String[] getNullPropertyNames(ProductDTO productDTO) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(productDTO);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullProperties.add(propertyName);
            }
        }
        return nullProperties.toArray(new String[0]);
    }
}
