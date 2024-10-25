package com.bootcamp.bc_calculator.calEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;
import com.bootcamp.bc_calculator.exception.CannotDivideZeroException;
import com.bootcamp.bc_calculator.exception.InvalidOperatorException;
import lombok.Getter;

@Getter
public enum Operation {
  ADD("add"), //
  SUBTRACT("sub"), //
  MULTIPLY("mul"), //
  DIVIDE("div"),//
  ;

  private String operator;

  private Operation(String operator) {
    this.operator = operator;
  }

  public String calculate(BigDecimal x, BigDecimal y) {
  return switch (this) {
      case ADD -> x.add(y).setScale(5).toString();
      case SUBTRACT -> x.subtract(y).setScale(5).toString();
      case MULTIPLY -> x.multiply(y).setScale(5).toString();
      // DividerZeroException
      case DIVIDE -> {
        if (BigDecimal.ZERO.equals(y)) {// "".equals(str)
          throw new CannotDivideZeroException(ErrorCode.CANNOT_DIVIDE_ZERO.getMessage());
        }
        yield x.divide(y,5,RoundingMode.HALF_UP).toString();
      }
      default -> //
          throw new InvalidOperatorException(ErrorCode.INVALID_OPERATOR.getMessage());
    };
  }
}
