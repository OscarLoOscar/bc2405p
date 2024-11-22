package com.bootcamp.bc_yahoo_finance.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockPrice;

@Component
public class Mapper {

  public StockEntity map(String stock) {
    return StockEntity.builder()//
        .symbol(stock)//
        .build();
  }

  public StockPrice map(YahooQuoteDTO.QuoteBody.Result result) {
    return StockPrice.builder()//
        .symbol(result.getSymbol())//
        .regularMarketTime(String.valueOf(result.getRegularMarketTime()))//
        .regularMarketPrice(String.valueOf(result.getRegularMarketPrice()))//
        .regularMarketChangePercent(String.valueOf(result.getRegularMarketChangePercent()))//
        .bid(String.valueOf(result.getBid()))//
        .bidSize(String.valueOf(result.getBidSize()))//
        .ask(String.valueOf(result.getAsk()))//
        .askSize(String.valueOf(result.getAskSize()))//
        // .apiDateTime(LocalDateTime.now(ZoneId.of("CTT")).toString())
        .apiDateTime(LocalDateTime.now().toString())//
        .marketUnixTime(String.valueOf(result.getRegularMarketTime()))
        .type(null)//
        .build();
  }

}
