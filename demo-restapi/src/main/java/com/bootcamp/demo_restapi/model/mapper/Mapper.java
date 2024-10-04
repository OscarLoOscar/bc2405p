package com.bootcamp.demo_restapi.model.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo_restapi.entity.UserEntity;
import com.bootcamp.demo_restapi.model.User;

@Component 
public class Mapper {

  public UserEntity map(User user) {
    return UserEntity.builder()//
        .name(user.getName())//
        .username(user.getUsername())//
        .email(user.getEmail())//
        .phone(user.getPhone())//
        .website(user.getWebsite())//
        .dummy(String.valueOf(""))//
        .build();
  }
}
