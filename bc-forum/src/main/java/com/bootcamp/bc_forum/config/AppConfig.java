package com.bootcamp.bc_forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.redis.RedisHelper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AppConfig {
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }
  
  @Bean
  RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory,
      ObjectMapper redisObjectMapper) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(factory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(
        new GenericJackson2JsonRedisSerializer(redisObjectMapper));
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  RedisHelper redisProfileHelper(RedisConnectionFactory factory, //
      ObjectMapper redisObjectMapper) {
    return new RedisHelper(factory, redisObjectMapper);
  }
}
