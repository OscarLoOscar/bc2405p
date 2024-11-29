package com.bootcamp.bc_yahoo_finance.model.line;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
// @NoArgsConstructor
@ToString
public class PriceLine<T> {
  private LineType lineType;
  private String stockSymbol;
  private Long stockId;

  @Builder.Default
  private Set<PricePoint<T>> points;// = new HashSet<>();

  public PriceLine(LineType lineType, String stockSymbol, Long stockId) {
    this.lineType = lineType;
    this.stockSymbol = stockSymbol;
    this.stockId = stockId;
    this.points = new HashSet<>();
  }

  public boolean add(PricePoint<T> pricePoint) {
    return this.points.add(pricePoint);
  }

  public void clear() {
    this.points.clear();
  }

  public enum LineType {
    MIN1, //
    MIN5, //
    MIN15, //
    MIN30, //
    HOUR1, //
    HOUR4, //
    DAY, //
    WEEK, //
    MONTH
  }

  public static void main(String[] args) {
    PriceLine<Candle> candleList =
        new PriceLine<>(LineType.MIN5, "0700.HK", 1L);

    candleList.add(new PricePoint<Candle>(
        LocalDateTime.of(LocalDate.of(2024, 11, 29), LocalTime.now())//
        , new Candle(456.78, 456.78, 456.78, 456.78))//
    );

    candleList.add(new PricePoint<Candle>(
        LocalDateTime.of(LocalDate.of(2024, 11, 29), LocalTime.now())//
        , new Candle(123.45, 123.45, 123.45, 123.45))//
    );
    System.out.println(candleList);
  }
}
