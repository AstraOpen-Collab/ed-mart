package com.edmart.product.mappers;

import com.edmart.product.dto.ProductDTO;
import com.edmart.product.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product){
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getSKU(),
                product.getCategoryId(),
                product.getInventoryId(),
                product.getPrice(),
                product.getOldPrice(),
                product.getNewPrice(),
                product.getImage()
        );
    }
}