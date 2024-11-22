package com.bootcamp.bc_yahoo_finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc_yahoo_finance.repository.StockSymbolRepository;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;

@Service
public class StockSymbolImpl implements StockSymbolService {

  @Autowired
  private StockSymbolRepository stockSymbolRepository;

  @Override
  public List<StockEntity> saveAll(List<StockEntity> symbolsList) {

    return stockSymbolRepository.saveAll(symbolsList);
  }

  @Override
  public void deleteAll() {
    stockSymbolRepository.deleteAll();
  }

  @Override
  public List<StockEntity> findAll() {
    return stockSymbolRepository.findAll();
  }

}
