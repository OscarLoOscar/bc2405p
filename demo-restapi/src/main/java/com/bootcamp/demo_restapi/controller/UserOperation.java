package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.bootcamp.demo_restapi.model.User;

public interface UserOperation {
  @GetMapping(value = "/users") //查詢
  List<User> getUsers();

  // @PostMapping    //CREATE -> 0 to 1 

  // @PatchMapping    // 改column

  // @DeleteMapping
}
