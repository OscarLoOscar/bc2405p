package com.bootcamp.bc_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CalController {

  @GetMapping("/cal")
  public String calculatorPage(Model model) {
    model.addAttribute("result", "");
    return "calculator";
  }
}
