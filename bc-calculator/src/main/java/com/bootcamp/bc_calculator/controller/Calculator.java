package com.bootcamp.bc_calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_calculator.model.APIRequest;
import com.bootcamp.bc_calculator.model.APIResponse;

public interface Calculator {

  @GetMapping("/operation")
  APIResponse calculator(@RequestParam String x, //
      @RequestParam String y, //
      @RequestParam String operation);

  @GetMapping("/operation/{x}/{y}/{operation}")
  APIResponse calculator2(@PathVariable String x, //
      @PathVariable String y, //
      @PathVariable String operation);

  @PostMapping("/operation")
  APIResponse calculator3(@RequestBody APIRequest aPIRequest);
}
