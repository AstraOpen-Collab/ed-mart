package com.edmart.client.inventory;

import java.io.Serializable;

public record InventoryRequest(
        Long inventoryId,
        Long productId,
        Integer quantity
) implements Serializable {
}
