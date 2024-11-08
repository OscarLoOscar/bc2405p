package com.bootcamp.bc_forum.controller.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.UserController;
import com.bootcamp.bc_forum.model.Mapper;
import com.bootcamp.bc_forum.model.ModifyMobile;
import com.bootcamp.bc_forum.model.UserCommentDTO;
import com.bootcamp.bc_forum.model.UserDTO;
import com.bootcamp.bc_forum.service.UserService;

@RestController
public class UserOperation implements UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private Mapper mapper;

  @Override
  public List<UserDTO> getAll() {
    return userService.getAll().stream()//
        .map(userEntity -> mapper.map(userEntity))//
        .collect(Collectors.toList());
  }

  @Override
  public List<UserDTO> getUserByID(String userID) {
    List<UserDTO> list = new ArrayList<>();
    list.add(mapper.map(userService.getUserByID(Long.parseLong(userID))));
    return list;
  }

  @Override
  public List<UserCommentDTO> getUserCommentByID(String userID) {
    return userService.getUserCommentByID(Long.parseLong(userID));
  }

  @Override
  public UserDTO modifyMobileNumber(ModifyMobile modifyMobile) {
    Long userID = Long.parseLong(modifyMobile.getUserID());
    String newMobile = modifyMobile.getNewMobileNum();
    return userService.modifyMobileNumber(userID, newMobile);
  }

  @Override
  public List<UserDTO> getDataFromRedis() {
return userService.getDataFromRedis();  }

}
