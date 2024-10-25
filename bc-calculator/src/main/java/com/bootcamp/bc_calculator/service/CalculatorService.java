package com.bootcamp.bc_calculator.service;

import java.math.BigDecimal;
import com.bootcamp.bc_calculator.model.APIResponse;

public interface CalculatorService {

  APIResponse calculator(BigDecimal x, //
      BigDecimal y, //
      String operation);

}
