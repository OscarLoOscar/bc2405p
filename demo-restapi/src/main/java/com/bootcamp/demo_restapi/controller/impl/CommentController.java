package com.bootcamp.demo_restapi.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo_restapi.controller.CommentOperation;
import com.bootcamp.demo_restapi.model.Comment;
import com.bootcamp.demo_restapi.service.CommentService;

@RestController
public class CommentController implements CommentOperation {

  @Autowired
  private CommentService commentService;

  @Override
  public List<Comment> getAllComments() {
    return List.of(commentService.getComment());
  }

}
