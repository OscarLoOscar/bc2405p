package com.bootcamp.demo_restapi.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
  private int code;

  public BusinessException(ErrorCode errorCode) {
    this(errorCode.getCode(), errorCode.getMessage());
  }

  public BusinessException(int code, String message) {
    super(message);
    this.code = code;
  }
}
