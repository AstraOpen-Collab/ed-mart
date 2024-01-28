package com.edmart.category.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class CategoryKafkaTopicConfig {

    @Bean
    public NewTopic categoryTopic(){
        return TopicBuilder.name("category-topic").build();
    }
}
