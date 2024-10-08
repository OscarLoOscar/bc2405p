package com.bootcamp.demo_api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_api.controller.impl.UserOperation;
import com.bootcamp.demo_api.model.User;
import com.bootcamp.demo_api.service.UserService;

@RestController // ->@Controller + @ResponseBody
public class UserController implements UserOperation {

  @Autowired
  private UserService userService;

  @Override
  public List<User> getUsers() {
    return userService.getUsers();
  }

}
