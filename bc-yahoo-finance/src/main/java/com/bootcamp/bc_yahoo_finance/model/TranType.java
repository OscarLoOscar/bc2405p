package com.bootcamp.bc_yahoo_finance.model;

import lombok.Getter;

@Getter
public enum TranType {
  FIVE_MINUTES("5M"),//
  DAILY("D"),//
  WEEKLY("W"),//
  MONTHLY("M"),//
  ;

  private String type;

  private TranType(String type){
    this.type=type;
  }
}
