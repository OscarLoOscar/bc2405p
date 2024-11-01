package com.bootcamp.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.JPHController;
import com.bootcamp.bc_forum.model.CommentPlaceHolder;
import com.bootcamp.bc_forum.model.ModifyMobile;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserDTO;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.service.JPHService;

@RestController
public class JPHOperation implements JPHController {

  @Autowired
  private JPHService jPHService;


  @Override
  public List<CommentPlaceHolder> getCommentRawdata() {
    return jPHService.getCommentRawdata();
  }

  @Override
  public List<PostPlaceHolder> getPostRawdata() {
    return jPHService.getPostRawdata();
  }

  @Override
  public List<UserPlaceHolder> getUserRawdata() {
    return jPHService.getUserRawdata();
  }


}
