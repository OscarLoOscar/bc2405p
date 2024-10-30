package com.bootcamp.bc_forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppConfig {
  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
