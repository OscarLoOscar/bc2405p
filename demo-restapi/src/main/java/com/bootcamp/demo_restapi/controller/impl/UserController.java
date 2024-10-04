package com.bootcamp.demo_restapi.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_restapi.controller.UserOperation;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.service.UserService;

@RestController
public class UserController implements UserOperation {

  @Autowired
  private UserService userService;

  @Override
  public List<User> getUsers() {
    return List.of(userService.getUsers());
  }

}
