package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserRequest;

// 1. PathVariable
// 2. RequestParam
// 3. RequestBody
public interface UserOperation {
  @GetMapping(value = "/users") // 查詢
  List<User> getUsers();

  @GetMapping(value = "/user") // 查詢
  User getUser(@RequestParam String userID);

  @PostMapping(value = "/create/user") // CREATE -> 0 to 1
  @ResponseStatus(HttpStatus.CREATED)
  User createNewUser(@RequestBody UserRequest userForm);

  // @PutMapping // 改row

  // @PatchMapping // 改column

  // @DeleteMapping
}
