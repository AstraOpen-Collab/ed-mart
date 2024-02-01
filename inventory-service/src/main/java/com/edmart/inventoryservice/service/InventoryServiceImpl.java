package com.edmart.inventoryservice.service;

import com.edmart.client.exceptions.InventoryNotFoundException;
import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.inventory.InventoryRequest;
import com.edmart.contracts.product.InventorySchema;
import com.edmart.inventoryservice.dtomapper.InventoryDtoMapper;
import com.edmart.inventoryservice.entity.Inventory;
import com.edmart.inventoryservice.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{


    private final InventoryRepository inventoryRepository;

    //private final ProductServiceClient productServiceClient;

    private final InventoryDtoMapper inventoryDtoMapper;

    @Override
    public Optional<InventoryRequest> getInventoryByProductId(Long inventoryId) {
        return Optional.ofNullable(inventoryRepository.findByProductId(inventoryId).map(inventoryDtoMapper)
                .orElseThrow(() -> new ProductNotFoundException("Product does not exist!")));
    }

    @Override
    public List<InventoryRequest> getAllItemInventories() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryDtoMapper)
                .collect(Collectors.toList());
    }


    @Override
    public void updateItemInventory(Long productId, InventoryRequest request) throws InventoryNotFoundException {
       Inventory inventory = inventoryRepository.findByProductId(productId)
               .orElseThrow(() -> new InventoryNotFoundException("Item does not exist in the Inventory system!"));
       Integer quantity = request.quantity() + inventory.getItemQuantity();

       inventory.setItemQuantity(quantity);

       inventoryRepository.save(inventory);
    }


    @KafkaListener(
            topics = "product_inventory_topic",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeInventoryEvent(InventorySchema inventorySchema) {
        log.info("Item received successfully: " + inventorySchema);

        Integer receivedQty = inventorySchema.getQuantity();
        Optional<Inventory> itemInventory = inventoryRepository.findByProductId(inventorySchema.getProductId());

        itemInventory.ifPresentOrElse(
                existingInventory -> {
                    existingInventory.setItemQuantity(existingInventory.getItemQuantity() + receivedQty);
                    inventoryRepository.save(existingInventory);
                },
                () -> {
                    Inventory inventory = new Inventory();
                    inventory.setProductId(inventorySchema.getProductId());
                    inventory.setItemQuantity(receivedQty);
                    inventory.setStatus(inventorySchema.getProductStatus().toString());

                    inventoryRepository.save(inventory);
                }
        );

        log.info("Successfully process the item with quantity : {}", inventorySchema.getQuantity());
    }

}
