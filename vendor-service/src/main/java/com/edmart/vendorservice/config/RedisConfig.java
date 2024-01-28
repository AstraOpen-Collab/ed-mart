//package com.edmart.vendorservice.config;//package com.astraworks.vendor.config;
//
//import com.edmart.client.vendor.VendorRecord;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//
//@Configuration
//public class RedisConfig {
//
//    @Bean
//    public RedisTemplate<String, VendorRecord> redisTemplate(
//            RedisConnectionFactory connectionFactory,
//            Jackson2JsonRedisSerializer<VendorRecord> serializer
//    ) {
//        RedisTemplate<String, VendorRecord> template = new RedisTemplate<>();
//        template.setConnectionFactory(connectionFactory);
//        template.setDefaultSerializer(serializer);
//        return template;
//    }
//
//    @Bean
//    public Jackson2JsonRedisSerializer<VendorRecord> jsonRedisSerializer() {
//        return new Jackson2JsonRedisSerializer<>(VendorRecord.class);
//    }
//}
//
