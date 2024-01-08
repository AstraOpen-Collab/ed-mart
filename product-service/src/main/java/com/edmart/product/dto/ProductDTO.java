package com.edmart.product.dto;

public record ProductDTO(Long productId,String name,String description,Long SKU,Long categoryId,Long inventoryId,
                         Double price,Double oldPrice,Double newPrice,byte[] image) {
}
