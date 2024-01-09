package com.edmart.product.dto;

import java.io.Serializable;

public record ProductDTO(Long productId, String name, String description, Long SKU, Long categoryId, Long inventoryId,
                         Double price, Double oldPrice, Double newPrice, byte[] image) implements Serializable {
}
