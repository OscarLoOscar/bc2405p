package com.bootcamp.demo_restapi.service;

import com.bootcamp.demo_restapi.entity.UserEntity;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserRequest;

public interface UserService {
  User[] getUsers();

  User getUser(Long userID);

  UserEntity createNewUser(String name, String email, String phone);

  User modifyUser(String userID,UserRequest userRequest);

  User updateEmail(String userID,String email);

  User updateMobile(String userID,String mobile);
}
