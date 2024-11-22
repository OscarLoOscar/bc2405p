package com.bootcamp.bc_yahoo_finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bootcamp.bc_yahoo_finance.entity.StockPrice;

public interface StockPriceRepository extends JpaRepository<StockPrice,Long>{
  
}
