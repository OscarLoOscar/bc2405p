package com.bootcamp.bc_yahoo_finance.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_yahoo_finance.infra.yahoofinance.CrumbManager;

@RestController
public class Test {

  @Autowired
  RestTemplate restTemplate;

  @GetMapping("/test")
  public String test(){
    return new CrumbManager(restTemplate).getCrumb();
  }
}
