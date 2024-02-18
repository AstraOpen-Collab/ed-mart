package com.edmart.elasticsearch.service;

import com.edmart.client.exceptions.ProductNotFoundException;
import com.edmart.client.product.*;
import com.edmart.elasticsearch.model.Product;
import com.edmart.elasticsearch.repository.ElasticSearchRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        Long productIdIndex = changeData.get("product_id").asLong();

        if(operation.equalsIgnoreCase("c")){
            log.info("$$$$$$$$$$$--Adding new record index---Product Schema Data: {}, operation: {}", changeData.get("name"), operation);

            elasticSearchRepository.save(setProductData(changeData));
        }else if(operation.equalsIgnoreCase("u")){
            log.info("$$$$$$$$$$$--updating existing record index with Id: {}", productIdIndex);

            Optional<Product> product = elasticSearchRepository.findById(productIdIndex);

            updateElasticSearchIndex(productIdIndex, product.get());
            log.info("Product Index updated Successfully!!");
        }else{
            deleteElasticIndexByProductId(productIdIndex);
        }

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

   public Optional<Product> getProductByProductId(Long productId){
        return Optional.ofNullable(elasticSearchRepository.findProductByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException("No such Product with this productId")));
   }

   public Iterable<Product> getAllProductIndexes(){
        return elasticSearchRepository.findAll();
   }

    public void deleteElasticIndexByProductId(Long productId){
        Optional<Product> product = Optional.ofNullable(elasticSearchRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Product Index notr Found!")));

        elasticSearchRepository.deleteById(productId);
    }

    public void updateElasticSearchIndex(Long productId, Product product){

        Optional<Product> productData = Optional.of(elasticSearchRepository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product Index not Found!")));


        BeanUtils.copyProperties(product, productData.get(), getNullPropertyNames(product));

        elasticSearchRepository.save(product);

    }

    private String[] getNullPropertyNames(Product product) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(product);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null) {
                nullProperties.add(propertyName);
            }
        }
        return nullProperties.toArray(new String[0]);
    }

}
