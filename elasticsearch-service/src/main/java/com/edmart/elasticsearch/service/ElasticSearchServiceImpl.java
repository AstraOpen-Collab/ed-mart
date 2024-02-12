package com.edmart.elasticsearch.service;

import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.exceptions.VendorNotFoundException;
import com.edmart.client.product.*;
import com.edmart.client.vendor.VendorClient;
import com.edmart.contracts.product.InventorySchema;
import com.edmart.contracts.product.ProductStatus;
import com.edmart.elasticsearch.model.Product;
import com.edmart.elasticsearch.repository.ElasticSearchRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElasticSearchServiceImpl {

    private final ElasticSearchRepository elasticSearchRepository;

    private final ProductServiceClient productServiceClient;

    @KafkaListener(
            topics = "cdc.public.product",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeProductTableEvents(ConsumerRecord<String, String> record) throws IOException {
        String key = record.key();
        String value = record.value();

        // Parse and process the Debezium message
        // Example: Use ObjectMapper to deserialize the JSON payload
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(value);
        JsonNode payload = jsonNode.get("payload");

        // Extract relevant information
        String operation = payload.get("op").asText(); // Operation: c (create), u (update), d (delete)
        JsonNode changeData = payload.get("after"); // After-change data




        log.info("$$$$$$$$$$$---Product Schema Data: {}, operation: {}", changeData.get("name"), operation);

        elasticSearchRepository.save(setProductData(changeData));

        log.info("################-----Payload:::: {}", changeData);

        // Process the change data based on the operation
        // Example: Log or persist the change data
        log.info("Received Debezium message: Key={}, Operation={}, ChangeData={}", key, operation, changeData);

//        Integer receivedQty = inventorySchema.getQuantity();
//        Optional<Inventory> itemInventory = inventoryRepository.findByProductId(inventorySchema.getProductId());
//
//        itemInventory.ifPresentOrElse(
//                existingInventory -> {
//                    existingInventory.setItemQuantity(existingInventory.getItemQuantity() + receivedQty);
//                    inventoryRepository.save(existingInventory);
//                },
//                () -> {
//                    Inventory inventory = new Inventory();
//                    inventory.setProductId(inventorySchema.getProductId());
//                    inventory.setItemQuantity(receivedQty);
//                    inventory.setStatus(inventorySchema.getProductStatus().toString());
//
//                    inventoryRepository.save(inventory);
//                }
//        );
//
//        log.info("Successfully process the item with quantity : {}", inventorySchema.getQuantity());
    }

    public Product setProductData(JsonNode changeData){

        Measurements measurements = new Measurements(
                changeData.get("length").asDouble(),
                changeData.get("width").asDouble(),
                changeData.get("length").asDouble(),
                changeData.get("weight").asDouble()
        );

        Units units = new Units(
                changeData.get("uos_id").asLong(),
                changeData.get("uop_id").asLong()
        );

        Prices prices = new Prices(
                changeData.get("old_price").decimalValue(),
                changeData.get("new_price").decimalValue()
        );

        String createdAtString = changeData.get("created_at").asText();
        String updatedAtString = changeData.get("updated_at").asText();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss");
//        LocalDateTime createdAt = LocalDateTime.parse(createdAtString, formatter);
//        LocalDateTime updatedAt = LocalDateTime.parse(updatedAtString, formatter);


        Product product = new Product();
        //product.setName(String.valueOf(jsonNodeMapper.toString()));
        product.setProductId(changeData.get("product_id").asLong());
        product.setName(changeData.get("name").asText());
        product.setDescription(changeData.get("description").asText());
        product.setCategoryId(changeData.get("category_id").asLong());
        product.setVendorId(changeData.get("vendor_id").asLong());
        product.setSKU(changeData.get("sku").asLong());
//        product.setCreatedAt(createdAt);
//        product.setUpdatedAt(updatedAt);
        product.setMeasurements(measurements);
        product.setPrices(prices);
        product.setUnits(units);
        product.setSKU(changeData.get("sku").asLong());
        product.setRating(changeData.get("rating").asInt());

        return product;
    }

    public void vendorCreateProduct(Long vendorId, ProductDTO productDTO) throws ProductNotFoundException {
        productServiceClient.vendorCreateProduct(vendorId, productDTO);
    }

}
