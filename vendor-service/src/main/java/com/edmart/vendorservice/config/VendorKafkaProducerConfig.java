package com.edmart.vendorservice.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class VendorKafkaProducerConfig {

    @Bean
    public NewTopic vendorKafkaTopic(){
        return TopicBuilder.name("vendor-topic")
                .build();
    }
}
