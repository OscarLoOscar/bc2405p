package com.bootcamp.bc_forum.model;

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
