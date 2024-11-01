package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.UserCommentDTO;
import com.bootcamp.bc_forum.model.UserDTO;

public interface UserController {

  @GetMapping("allUserPostComment")
  public List<UserDTO> getAll();
  
  @GetMapping()
  public List<UserDTO> getUserByID(@RequestParam String userID);

  @GetMapping("getUserCommentByID") //?userID=1
  public List<UserCommentDTO> getUserCommentByID(@RequestParam String userID);

}