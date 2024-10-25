package com.bootcamp.bc_calculator.exception;

import lombok.Getter;

@Getter
public class CannotDivideZeroException extends IllegalArgumentException {
  private String message;

  public CannotDivideZeroException(String message) {
    this.message = message;
  }
}
