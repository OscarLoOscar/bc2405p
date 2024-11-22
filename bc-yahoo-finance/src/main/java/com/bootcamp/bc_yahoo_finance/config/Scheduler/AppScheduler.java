package com.bootcamp.bc_yahoo_finance.config.Scheduler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.bootcamp.bc_yahoo_finance.redis.RedisHelper;
import com.bootcamp.bc_yahoo_finance.service.StockPriceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class AppScheduler {
  @Autowired
  private StockPriceService stockPriceService;

  @Autowired
  private RedisHelper redisHelper;

  /**
   * 1. EveryMinute
   * 
   * @Scheduled(cron = "0 * * * * ?")
   * 
   * 2.Once per day at midnight
   * 
   * @Scheduled(cron = "0 0 0 * * ?")
   * 
   * 3.Once per week on Sunday at midnight
   * 
   * @Scheduled(cron = "0 0 0 ? * SUN")
   * 
   * 4.Run only on weekdays (Monday to Friday) at 9:00 AM
   * 
   *  @Scheduled(cron = "0 0 9 ? * MON-FRI")
   * 
   * 5.Run on the last day of every month at 11:59 PM
   * 
   * @Scheduled(cron = "0 59 23 L * ?")
   * 
   * 6.Run on the first day of every month at 12:00 AM
   * 
   * @Scheduled(cron = "0 0 0 1 * ?")
   */

  // CRON job
  // syntax : (cron = "0 0/1 * * * ?") per minute
  @Scheduled(cron = "0 0/5 9-16 * * MON-FRI")
  public void cronJob() throws JsonMappingException, JsonProcessingException {

    String[] symbol = redisHelper.get("STOCK-LIST", String[].class);
    List<String> symbols = Arrays.stream(symbol)//
        .collect(Collectors.toList());
    stockPriceService.save(symbols);
    System.out.println("Success 5 mins CRON job");
  }

}
