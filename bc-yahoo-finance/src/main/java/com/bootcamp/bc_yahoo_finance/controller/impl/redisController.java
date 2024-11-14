package com.bootcamp.bc_yahoo_finance.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_yahoo_finance.redis.RedisHelper;
import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
public class redisController {
  @Autowired
  private RedisHelper redisHelper;

  @GetMapping("redis")
  public String[] getMethodName() throws JsonProcessingException {
      return redisHelper.get("STOCK-LIST", String[].class);
  }
  
}
