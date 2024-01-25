package com.edmart.client.product;

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
        Integer rating,
        Long vendorId
) implements Serializable {
}
