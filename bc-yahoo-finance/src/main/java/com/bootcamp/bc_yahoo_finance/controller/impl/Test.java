package com.bootcamp.bc_yahoo_finance.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_yahoo_finance.entity.StockPrice;
import com.bootcamp.bc_yahoo_finance.service.StockPriceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class Test {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockPriceService stockPriceService;

  @GetMapping("/test")
  public List<StockPrice> test(@RequestParam List<String> symbols)
      throws JsonMappingException, JsonProcessingException {
    return stockPriceService.save(symbols);
  }
}
