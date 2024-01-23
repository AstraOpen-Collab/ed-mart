package com.edmart.product.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class ProductKafkaTopicConfig {


    @Bean
    public NewTopic ProducerKafkaTopic(){
        return TopicBuilder.name("product-topic")
                .build();
    }
}
