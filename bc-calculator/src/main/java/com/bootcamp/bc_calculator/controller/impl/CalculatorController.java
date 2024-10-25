package com.bootcamp.bc_calculator.controller.impl;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_calculator.controller.Calculator;
import com.bootcamp.bc_calculator.model.APIRequest;
import com.bootcamp.bc_calculator.model.APIResponse;
import com.bootcamp.bc_calculator.service.CalculatorService;

@RestController
public class CalculatorController implements Calculator {

  @Autowired
  private CalculatorService calculatorService;

  @Override
  public APIResponse calculator(String x, String y, String operation) {
    BigDecimal convertX = BigDecimal.valueOf(Double.valueOf(x));
    BigDecimal convertY = BigDecimal.valueOf(Double.valueOf(y));

    return calculatorService.calculator(convertX, convertY, operation);
  }

  @Override
  public APIResponse calculator2(String x, String y, String operation) {
    BigDecimal convertX = BigDecimal.valueOf(Double.valueOf(x));
    BigDecimal convertY = BigDecimal.valueOf(Double.valueOf(y));

    return calculatorService.calculator(convertX, convertY, operation);
  }

  @Override
  public APIResponse calculator3(APIRequest apiRequest) {
    BigDecimal convertX = BigDecimal.valueOf(Double.valueOf(apiRequest.getX()));
    BigDecimal convertY = BigDecimal.valueOf(Double.valueOf(apiRequest.getY()));
   
    return calculatorService.calculator(convertX, convertY,
        apiRequest.getOperation());
  }

}
