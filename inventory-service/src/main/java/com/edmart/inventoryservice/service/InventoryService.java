package com.edmart.inventoryservice.service;

import com.edmart.client.exceptions.InventoryNotFoundException;
import com.edmart.client.inventory.InventoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    Optional<InventoryRequest> getInventoryByProductId(Long productId);

    List<InventoryRequest> getAllItemInventories();

    void updateItemInventory(Long productId, InventoryRequest request) throws InventoryNotFoundException;
}
