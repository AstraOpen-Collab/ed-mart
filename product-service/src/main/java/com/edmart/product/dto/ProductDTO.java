package com.edmart.product.dto;

import com.edmart.client.product.Measurements;
import com.edmart.client.product.Prices;
import com.edmart.client.product.Units;

import java.io.Serializable;

public record ProductDTO(
        Long productId,
        String name,
        String description,
        Long SKU,
        Long categoryId,

        Prices prices,
        Units units,
        Measurements measurements,
        byte[] image,
        Integer rating
) implements Serializable {
}
