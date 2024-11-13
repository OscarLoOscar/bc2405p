package com.bootcamp.bc_yahoo_finance.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  RUNTIME_ERROR(666,"runtime error"),//
  UNKNOWN_ERROR(777,"unknown error"),//
  INVALID_INPUT(8,"invalid input.")
  ;

  private int code;
  private String message;

  private ErrorCode(int code , String message){
    this.code=code;
    this.message=message;
  }
}
