package com.bootcamp.bc_calculator.service.impl;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_calculator.calEnum.ErrorCode;
import com.bootcamp.bc_calculator.calEnum.Operation;
import com.bootcamp.bc_calculator.exception.InvalidOperatorException;
import com.bootcamp.bc_calculator.model.APIRequest;
import com.bootcamp.bc_calculator.model.APIResponse;
import com.bootcamp.bc_calculator.service.CalculatorService;

@Service // Don't miss this annotation
public class CalculatorServiceImpl implements CalculatorService {
  @Override
  public APIResponse calculator(BigDecimal x, //
      BigDecimal y, //
      String operation) {
    Operation op = this.map(operation);

    return APIResponse.builder()//
        // .x(new String(x))//
        .x(String.valueOf(x))//
        .y(String.valueOf(y))//
        .operation(op.getOperator())//
        .result(op.calculate(x, y))//
        .build();
  }

  // how to use String operation make Connection with enum Operation ???
  // convert input param String to enum Operation
  private Operation map(String operator) {
    for (Operation op : Operation.values()) {// Enum.values() return Enum[]
      if (op.getOperator().equals(operator)) {
        return op;
      }
    }
    throw new InvalidOperatorException(ErrorCode.INVALID_OPERATOR.getMessage());
  }
}
