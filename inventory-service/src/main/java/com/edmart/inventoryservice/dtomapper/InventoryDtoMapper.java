package com.edmart.inventoryservice.dtomapper;

import com.edmart.client.inventory.InventoryRequest;
import com.edmart.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class InventoryDtoMapper implements Function<Inventory, InventoryRequest> {


    @Override
    public InventoryRequest apply(Inventory inventory) {
        return new InventoryRequest(
                inventory.getInventoryId(),
                inventory.getProductId(),
                inventory.getItemQuantity(),
                inventory.getStatus()
        );
    }
}
