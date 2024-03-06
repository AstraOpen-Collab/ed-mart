package com.edmart.discountservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class DiscountKafkaProducerConfig {

    @Bean
    public NewTopic productDiscountTopic(){
        return TopicBuilder.name("product_discount_topic")
                .partitions(3)
                .compact()
                .build();
    }

    @Bean
    @Primary
    public NewTopic categoryDiscountTopic(){
        return TopicBuilder.name("category_discount_topic")
                .partitions(3)
                .compact()
                .build();
    }
}
