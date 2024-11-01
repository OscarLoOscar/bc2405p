package com.bootcamp.bc_forum.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Don't miss this annotation
public class GlobalExceptionalHandler {

  @ExceptionHandler({UserNotFindException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  public ErrorResponse handleUserNotFindException() {
    return ErrorResponse.builder()//
        .code(ErrorCode.USER_NOT_FOUND.getCode())//
        .message(ErrorCode.USER_NOT_FOUND.getMessage())//
        .build();
  }

  @ExceptionHandler({NumberFormatException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  public ErrorResponse handleInvalidInputException() {
    return ErrorResponse.builder()//
        .code(ErrorCode.USER_NOT_FOUND.getCode())//
        .message(ErrorCode.USER_NOT_FOUND.getMessage())//
        .build();
  }

  @ExceptionHandler({RuntimeException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  public ErrorResponse handleRuntimeException() {
    return ErrorResponse.builder()//
        .code(ErrorCode.RUNTIME_ERROR.getCode())//
        .message(ErrorCode.RUNTIME_ERROR.getMessage())//
        .build();
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  public ErrorResponse handleException() {
    return ErrorResponse.builder()//
        .code(ErrorCode.UNKNOWN_ERROR.getCode())//
        .message(ErrorCode.UNKNOWN_ERROR.getMessage())//
        .build();
  }
}
