package com.bootcamp.demo_restapi.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.demo_restapi.controller.DTOOperation;
import com.bootcamp.demo_restapi.model.UserPostDTO;
import com.bootcamp.demo_restapi.service.DTOService;

@RestController
public class DTOController implements DTOOperation {
  @Autowired
  private DTOService dtoService;

  @Override
  public UserPostDTO getUserPostDTO(String userID, String postID) {
    return dtoService.getUserPost(Long.parseLong(userID), Long.parseLong(postID));
  }
}