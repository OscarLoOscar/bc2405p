package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.entity.StockPrice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface StockPriceService {
  List<StockPrice>  save(List<String> symbol)throws JsonMappingException, JsonProcessingException;
}
