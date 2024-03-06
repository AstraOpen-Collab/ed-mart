package com.edmart.client.product;

public record ProductDeleteEvent(
        Long productId,
        String productName
) {
}
