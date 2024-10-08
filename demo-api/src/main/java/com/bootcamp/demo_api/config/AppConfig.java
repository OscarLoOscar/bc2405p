package com.bootcamp.demo_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
  
  //CAll API 
  @Bean
  RestTemplate restTemplate(){
   return new RestTemplate();
  }
}
