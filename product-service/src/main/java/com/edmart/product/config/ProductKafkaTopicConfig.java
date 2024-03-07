package com.edmart.product.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;


@Configuration
public class ProductKafkaTopicConfig {


    @Bean
    @Primary
    public NewTopic ProductInventoryTopic(){
        return TopicBuilder.name("product_inventory_topic")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }

    @Bean
    public NewTopic ProductInventoryTopic1(){
        return TopicBuilder.name("product_delete_topic")
                .partitions(3)
                .replicas(1)
                .compact()
                .build();
    }


}
