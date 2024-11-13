package com.bootcamp.bc_yahoo_finance.service;

import java.util.List;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;

public interface StockSymbolService {
  List<StockEntity> saveAll(List<StockEntity> symbolsList);

  void deleteAll();
}
