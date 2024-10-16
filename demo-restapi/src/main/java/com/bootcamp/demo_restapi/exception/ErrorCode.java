package com.bootcamp.demo_restapi.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  OK(100, "Response OK."), //
  CANNOT_LOGIN(200, "cannot login."), //
  USER_NOT_FIND_EXPECTION(300,"User not exist."),//
  API_ERROR(500, "third part api error"),//
  BUSSINESS_EXCEPTION(600,"bussiness exception")
  ;

  private int code;
  private String message;

  private ErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
