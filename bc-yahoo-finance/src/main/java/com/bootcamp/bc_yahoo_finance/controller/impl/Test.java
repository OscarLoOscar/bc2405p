package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_yahoo_finance.infra.yahoofinance.YHRestClient;
import com.bootcamp.bc_yahoo_finance.model.YahooQuoteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class Test {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/test")
  public YahooQuoteDTO test() throws JsonMappingException, JsonProcessingException{
    return new YHRestClient(restTemplate).getQuote(List.of("0388.HK,0700.HK"));
  }
}
