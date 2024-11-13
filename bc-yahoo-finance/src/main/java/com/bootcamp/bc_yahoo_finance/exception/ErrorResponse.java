package com.bootcamp.bc_yahoo_finance.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Builder
public class ErrorResponse {
  private int code;
  private String message;
}
