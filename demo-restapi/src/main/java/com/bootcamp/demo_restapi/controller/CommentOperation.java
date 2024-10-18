package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.demo_restapi.model.Comment;

public interface CommentOperation {
  @GetMapping("/comment")
  List<Comment> getAllComments();

}
