package com.bootcamp.demo_restapi.infra;

import lombok.Getter;

@Getter
public enum Scheme {
  HTTP("http"), //
  HTTPS("https"), //
  ;

  private String protocol;

  Scheme(String protocol) {
    this.protocol = protocol;
  }
}
