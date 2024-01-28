package com.edmart.contracts.product;

import com.edmart.client.product.ProductStatus;

import java.io.Serializable;

public record ProductInventorySchema(
        Long productId,
        Integer quantity,
        ProductStatus productStatus
) implements Serializable {
}
