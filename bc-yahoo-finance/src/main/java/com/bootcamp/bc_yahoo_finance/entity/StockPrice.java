package com.bootcamp.bc_yahoo_finance.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TSTOCKS_PRICE")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"stock_id", "symbol", "regularMarketTime",
    "regularMarketPrice", //
    "regularMarketChangePercent", "bid", "bidSize", "ask", "askSize", "type",
    "apiDateTime", "marketUnixTime", "stock"})
public class StockPrice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty("stock_id")
  private Long id;

  private String symbol;

  private String regularMarketTime;

  private String regularMarketPrice;

  private String regularMarketChangePercent;

  private String bid;

  private String bidSize;

  private String ask;

  private String askSize;

  private String type;
  // 5M = 5 mins , D =Daily , W = weekly , M = Monthly

  private String apiDateTime;

  private String marketUnixTime;

  @ManyToOne
  @JoinColumn(name = "stock_id")
  private StockEntity stock;
}
