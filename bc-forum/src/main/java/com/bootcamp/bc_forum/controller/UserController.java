package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.entity.UserEntity;

public interface UserController {

  
  @GetMapping("allUserPostComment")
  public List<UserEntity> getAll();
  
  @GetMapping()
  public List<UserEntity> getUserByID(@RequestParam String userID);

}