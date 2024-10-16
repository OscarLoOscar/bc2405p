package com.bootcamp.demo_restapi.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_restapi.controller.UserOperation;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserRequest;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.service.UserService;

@RestController
public class UserController implements UserOperation {

  @Autowired
  private UserService userService;

  @Autowired
  private Mapper mapper;

  @Override
  public List<User> getUsers() {
    return List.of(userService.getUsers());
  }

  @Override
  public User getUser(String userID) {
    return userService.getUser(Long.parseLong(userID));
  }

  @Override
  public User createNewUser(UserRequest userForm) {
    String name = userForm.getName();
    String email = userForm.getEmail();
    String phone = userForm.getPhone();
    return mapper.map(userService.createNewUser(name, email, phone));
  }

  @Override
  public User modifyUser(String userID, UserRequest userRequest) {
    return userService.modifyUser(userID, userRequest);
  }

  @Override
  public User updateEmail(String userID, String email) {
    return userService.updateEmail(userID, email);
  }

  @Override
  public User updateMobile(String userID, String mobile) {
    return userService.updateMobile(userID, mobile);
  }

}
