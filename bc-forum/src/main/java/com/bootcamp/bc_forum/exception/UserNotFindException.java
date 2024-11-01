package com.bootcamp.bc_forum.exception;

public class UserNotFindException extends RuntimeException {

  public UserNotFindException(String message) {
    super(message);
  }
}
