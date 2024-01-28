package com.edmart.product.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;


@Configuration
public class ProductKafkaTopicConfig {


//    @Bean
//    public NewTopic ProducerKafkaTopic(){
//        return TopicBuilder.name("product-topic")
//                .build();
//    }

    @Bean
    public NewTopic ProductInventoryTopic(){
        return TopicBuilder.name("product_inventory_topic")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

}
