package com.edmart.inventoryservice.controller;

import com.edmart.client.inventory.InventoryRequest;
import com.edmart.inventoryservice.service.InventoryServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Data
@Slf4j
@RestController
@RequestMapping("/api/v1/inventories")
@CrossOrigin(origins = {"http://localhost:8083", "http://localhost:8090"} )
public class InventoryController {


    private final InventoryServiceImpl inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryRequest>> getAllInventories(){
        log.info("getting all available inventories: {}", inventoryService.getAllItemInventories());
        return ResponseEntity.ok()
                .body(inventoryService.getAllItemInventories());
    }

    @GetMapping("/products/{itemId}")
    public ResponseEntity<Optional<InventoryRequest>> getItemInventory(@PathVariable("itemId") Long itemId){
        log.info("getting item inventory...with Id: {}", itemId);
        return ResponseEntity.ok()
                .body(inventoryService.getInventoryByProductId(itemId));
    }
}
