package com.bootcamp.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.bc_forum.controller.RawDataController;
import com.bootcamp.bc_forum.model.CommentPlaceHolder;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.service.UserService;

@RestController
public class RawDataOperation implements RawDataController {

  @Autowired
  private UserService userService;

  // @Autowired
  // private PostService postService;

  // @Autowired
  // private CommentService commentService;

  @Override
  public List<CommentPlaceHolder> getCommentRawdata() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'getCommentRawdata'");
  }

  @Override
  public List<PostPlaceHolder> getPostRawdata() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'getPostRawdata'");
  }

  @Override
  public List<UserPlaceHolder> getUserRawdata() {
    return userService.getRawdata();
  }

}
