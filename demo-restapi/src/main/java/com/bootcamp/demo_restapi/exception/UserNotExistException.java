package com.bootcamp.demo_restapi.exception;

import lombok.Getter;

@Getter
public class UserNotExistException extends BusinessException {

  private int code;

  public UserNotExistException(ErrorCode errorCode) {
    super(errorCode);
    this.code=errorCode.getCode();
  }
}
