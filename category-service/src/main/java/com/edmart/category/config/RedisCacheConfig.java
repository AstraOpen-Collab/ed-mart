//package com.edmart.category.config;
//
//import com.edmart.category.dto.CategoryDTO;
//import com.edmart.category.entity.Category;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
//import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.*;
//
//
//import java.time.Duration;
//
//@Configuration
//@EnableCaching
//public class RedisCacheConfig extends CachingConfigurerSupport {
//
//    @Bean
//    public RedisTemplate<String, Category> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<String, Category> template = new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Category.class));
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setHashKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Category.class));
//        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Category.class));
//        return template;
//    }
//
//    @Bean
//    public RedisCacheConfiguration redisCacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Category.class)));
//    }
//
//
////    private RedisSerializer<?> redisSerializer() {
////        ObjectMapper objectMapper = new ObjectMapper();
////        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////       // objectMapper.activateDefaultTyping(PolymorphicTypeValidator, ObjectMapper.DefaultTyping);
////        objectMapper.addMixIn(CategoryDTO.class, CategoryDTOMixIn.class);
////
////        return new GenericJackson2JsonRedisSerializer(objectMapper);
////    }
////
////    private org.springframework.data.redis.cache.RedisCacheConfiguration redisCacheConfiguration() {
////        return org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig()
////                .entryTtl(redisTtl())
////                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer()));
////    }
////
////    @Bean
////    public ObjectMapper objectMapper() {
////        return JsonMapper.builder()
////                .findAndAddModules()
////                .activateDefaultTyping(BasicPolymorphicTypeValidator.builder().allowIfBaseType("com.edmart.category.").build())
////                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
////                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
////                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
////                .disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES)
////                .build();
////    }
////
////    private Duration redisTtl() {
////        // Set your desired TTL (time-to-live) for cached entries
////        return Duration.ofMinutes(10);
////    }
////
////    @JsonTypeInfo(
////            use = JsonTypeInfo.Id.CLASS,
////            include = JsonTypeInfo.As.PROPERTY,
////            property = "@class"
////    )
////    private abstract static class CategoryDTOMixIn {}
//
//}
