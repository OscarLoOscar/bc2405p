package com.bootcamp.bc_yahoo_finance.util;

import lombok.Getter;

@Getter
public enum Scheme {
  HTTPS("https"),//
  HTTP("http"),//
  ;

  private String scheme;

  private Scheme(String scheme){
    this.scheme=scheme;
  }
  
}
