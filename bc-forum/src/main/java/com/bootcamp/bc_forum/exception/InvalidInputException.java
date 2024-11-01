package com.bootcamp.bc_forum.exception;

public class InvalidInputException extends NumberFormatException{
  public InvalidInputException(String message){
    super(message);
  }
}
