package com.bootcamp.bc_yahoo_finance.model.line;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Base {
  private double close;
}