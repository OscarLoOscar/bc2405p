package com.bootcamp.demo_restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.demo_restapi.model.UserPostDTO;

public interface DTOOperation {
  @GetMapping("/userpost/{userID}")
  @ResponseStatus(HttpStatus.OK)
  UserPostDTO getUserPostDTO(@PathVariable String userID, //
      @RequestParam(defaultValue = "0") String postID);
      // 
}
