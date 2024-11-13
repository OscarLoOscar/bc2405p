package com.bootcamp.demo_docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
  
  @GetMapping("hello")
  public String getMethodName() {
      return "testing Docker";
  }
  
}
