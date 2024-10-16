package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  // PutMapping -> update the row of database -> by Id find something , than modify
  @PutMapping("/user/{userID}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  User modifyUser(@PathVariable String userID,
      @RequestBody UserRequest userRequest);
  // {
  // "username" : "TEST"
  // "email" : "TEST@ETST.com"
  // "phone": "98765432"
  // }

  // @PatchMapping // 改column
  // can not be same endpoint
  @PatchMapping("/email/{userID}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  User updateEmail(@PathVariable String userID, @RequestParam String email);

  @PatchMapping("/phone/{userID}") // "/phone/1?mobile=98765432"
  @ResponseStatus(HttpStatus.ACCEPTED)
  User updateMobile(@PathVariable String userID, @RequestParam String mobile);

  // @DeleteMapping
}
