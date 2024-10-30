package com.bootcamp.bc_forum.controller.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.UserController;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.service.UserService;

@RestController
public class UserOperation implements UserController {

  @Autowired
  private UserService userService;

  @Override
  public List<UserEntity> getAll() {
    return userService.getAll();
  }

  @Override
  public List<UserEntity> getUserByID(String userID) {
    List<UserEntity> list = new ArrayList<>();
    list.add(userService.getUserByID(Long.valueOf(userID)));
    return list;
  }

}
