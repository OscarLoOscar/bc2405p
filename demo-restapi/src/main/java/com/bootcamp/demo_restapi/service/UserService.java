package com.bootcamp.demo_restapi.service;

import com.bootcamp.demo_restapi.model.User;

public interface UserService {
  User[] getUsers();

  User getUser(Long userID);
}
