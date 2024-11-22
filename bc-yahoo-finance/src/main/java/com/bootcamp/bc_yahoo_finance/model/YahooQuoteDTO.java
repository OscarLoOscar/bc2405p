package com.bootcamp.bc_yahoo_finance.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class YahooQuoteDTO {
  @JsonProperty("quoteResponse")
  private QuoteBody body;

  @Getter
  public static class QuoteBody {
    @JsonProperty("result")
    private List<Result> result;
    private QuoteError error;

    @Getter
    public static class Result {
      private String language;
      private String region;
      private String quoteType;
      private String typeDisp;
      private String quoteSourceName;
      private Boolean triggerable;
      private String customPriceAlertConfidence;
      private String exchange;
      private String shortName;
      private String longName;
      private String messageBoardId;
      private String exchangeTimezoneName;
      private String exchangeTimezoneShortName;
      private Long gmtOffSetMilliseconds;
      private String market;
      private String esgPopulated;
      private Double regularMarketChangePercent;
      private Double regularMarketPrice;
      private String currency;
      private String marketState;
      private Boolean hasPrePostMarketData;
      private Long firstTradeDateMilliseconds;
      private Long priceHint;
      private Double regularMarketChange;
      private Long regularMarketTime;
      private Double regularMarketDayHigh;
      private String regularMarketDayRange;
      private Double regularMarketDayLow;
      private Long regularMarketVolume;
      private Double trailingAnnualDividendYield;
      private Double epsTrailingTwelveMonths;
      private Long sharesOutstanding;
      private Double bookValue;
      private Double fiftyDayAverage;
      private Double fiftyDayAverageChange;
      private Double fiftyDayAverageChangePercent;
      private Double twoHundredDayAverage;
      private Double twoHundredDayAverageChange;
      private Double twoHundredDayAverageChangePercent;
      private Long marketCap;
      private Double priceToBook;
      private Long sourceInterval;
      private Long exchangeDataDelayedBy;
      private Double regularMarketPreviousClose;
      private Double bid;
      private Double ask;
      private Long bidSize;
      private Long askSize;
      private String fullExchangeName;
      private String financialCurrency;
      private Double regularMarketOpen;
      private Long averageDailyVolume3Month;
      private Long averageDailyVolume10Day;
      private Double fiftyTwoWeekLowChange;
      private Double fiftyTwoWeekLowChangePercent;
      private String fiftyTwoWeekRange;
      private Double fiftyTwoWeekHighChange;
      private Double fiftyTwoWeekHighChangePercent;
      private Double fiftyTwoWeekLow;
      private Double fiftyTwoWeekHigh;
      private Double fiftyTwoWeekChangePercent;
      private Long earningsTimestamp;
      private Long earningsTimestampStart;
      private Long earningsTimestampEnd;
      private Long earningsCallTimestampStart;
      private Long earningsCallTimestampEnd;
      private Boolean isEarningsDateEstimate;
      private Double trailingAnnualDividendRate;
      private Double dividendRate;
      private Double dividendYield;
      private Double forwardPE;
      private Double epsForward;
      private Double epsCurrentYear;
      private Boolean tradeable;
      private Boolean cryptoTradeable;
      private Double trailingPE;
      private Double priceEpsCurrentYear;
      private String averageAnalystRating;
      private String symbol;
    }
  }

  @Getter
  public static class QuoteError {
    private String code;
    private String message;
  }
}
