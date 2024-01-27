package com.edmart.inventoryservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class InventoryKafkaConfig {

    @Bean
    public NewTopic InventoryTopicConfig(){
        return TopicBuilder.name("inventory_topic")
                .compact()
                .replicas(1)
                .partitions(3)
                .build();
    }
}
