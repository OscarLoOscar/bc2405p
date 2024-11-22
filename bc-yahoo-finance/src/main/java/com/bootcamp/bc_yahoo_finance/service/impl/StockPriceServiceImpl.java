package com.bootcamp.bc_yahoo_finance.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_yahoo_finance.entity.StockEntity;
import com.bootcamp.bc_yahoo_finance.entity.StockPrice;
import com.bootcamp.bc_yahoo_finance.infra.yahoofinance.YHRestClient;
import com.bootcamp.bc_yahoo_finance.model.Mapper;
import com.bootcamp.bc_yahoo_finance.model.TranType;
import com.bootcamp.bc_yahoo_finance.model.YahooQuoteDTO;
import com.bootcamp.bc_yahoo_finance.repository.StockPriceRepository;
import com.bootcamp.bc_yahoo_finance.service.StockPriceService;
import com.bootcamp.bc_yahoo_finance.service.StockSymbolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class StockPriceServiceImpl implements StockPriceService {

  @Autowired
  private StockPriceRepository stockPriceRepository;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private Mapper mapper;

  @Autowired
  private StockSymbolService stockSymbolService;

  @Override
  public List<StockPrice> save(List<String> symbol)
      throws JsonMappingException, JsonProcessingException {
    YahooQuoteDTO yahooQuoteDTO = new YHRestClient(restTemplate)//
        .getQuote(symbol);
    List<YahooQuoteDTO.QuoteBody.Result> result =
        yahooQuoteDTO.getBody().getResult();

    List<StockEntity> stockEntitys = stockSymbolService.findAll();
    List<StockPrice> stockPrices = result.stream()//
        .map(stock -> {
          StockPrice stockPrice = this.mapper.map(stock);
          stockPrice.setType(TranType.FIVE_MINUTES.getType());

          stockEntitys.forEach(stockEntity -> {
            if (stockEntity.getSymbol().equals(stockPrice.getSymbol())) {
              stockPrice.setStock(stockEntity);
            }
          });
          return stockPrice;
        })//
        .collect(Collectors.toList());

    stockPriceRepository.saveAll(stockPrices);

    return stockPrices;
  }

}
