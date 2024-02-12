package com.edmart.elasticsearch.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class ElasticSearchKafkaConfig {


    @Bean
    public NewTopic elasticSearchTopic(){
        return TopicBuilder.name("elastic_search_topic").build();
    }
}
