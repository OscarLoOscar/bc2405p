package com.bootcamp.demo_restapi.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CannotLoginException.class)
  public ErrorResponse cannotLoginExceptionHandler() {
    return ErrorResponse.builder()//
        .code(ErrorCode.CANNOT_LOGIN.getCode())//
        .message(ErrorCode.CANNOT_LOGIN.getMessage())//
        .build();
  }
  @ExceptionHandler(UserNotExistException.class)
  public ErrorResponse userNotExistExceptionHandler() {
    return ErrorResponse.builder()//
        .code(ErrorCode.USER_NOT_FIND_EXPECTION.getCode())//
        .message(ErrorCode.USER_NOT_FIND_EXPECTION.getMessage())//
        .build();
  }

  // will throw error if you catching same Exception class
  @ExceptionHandler(BusinessException.class)
  public ErrorResponse businessExceptionHandler() {
    return ErrorResponse.builder()//
        .code(ErrorCode.BUSSINESS_EXCEPTION.getCode())//
        .message(ErrorCode.BUSSINESS_EXCEPTION.getMessage())//
        .build();
  }

  @ExceptionHandler(Exception.class)
  public ErrorResponse businessExceptionHandler2(ErrorCode errorCode) {
    return ErrorResponse.builder()//
        .code(errorCode.getCode())//
        .message(errorCode.getMessage())//
        .build();
  }

}
