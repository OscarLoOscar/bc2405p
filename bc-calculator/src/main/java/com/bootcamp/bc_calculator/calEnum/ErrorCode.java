package com.bootcamp.bc_calculator.calEnum;
import lombok.Getter;

@Getter
public enum ErrorCode {
  INVALID_INPUT(9, "Invalid Input."), //
  INVALID_OPERATOR(10, "operator must be + , - , * , / "), //
  SERVER_ERROR(11, "Server Error."),//
  CANNOT_DIVIDE_ZERO(12,"y can not be Zero"),//

  ;

  private Integer code;
  private String message;

  private ErrorCode(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

}
