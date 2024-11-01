package com.bootcamp.bc_forum.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
  USER_NOT_FOUND(1,"user not exist."),//
  POST_NOT_FOUND(2,"post not exist."),//
  COMMENT_NOT_FOUND(3,"comment not exist."),//
  USER_EMAIL_NOT_FOUND(4,"user email not exist."),//
  USER_MOBILE_NOT_FOUND(5,"user mobile number not exist."),//
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
