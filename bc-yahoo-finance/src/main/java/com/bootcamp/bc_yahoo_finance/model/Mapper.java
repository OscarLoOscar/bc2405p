package com.bootcamp.bc_yahoo_finance.model;

import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;

@Component
public class Mapper {

  public StockEntity map(String stock) {
    return StockEntity.builder()//
        .symbol(stock)//
        .build();
  }
  
}
