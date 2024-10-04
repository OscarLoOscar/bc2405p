package com.bootcamp.demo_restapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootcamp.demo_restapi.model.UserPostDTO;

public interface DTOOperation {
  @GetMapping("/userpost/{userID}")
  UserPostDTO getUserPostDTO(@PathVariable String userID, //
      @RequestParam(defaultValue = "0") String postID);
}
