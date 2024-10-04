package com.bootcamp.demo_restapi.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.demo_restapi.controller.PostOperation;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.service.PostService;

@RestController
public class PostController implements PostOperation {

  @Autowired
  private PostService postService;

  @Override
  public List<Post> getPosts() {
    return List.of(postService.getPosts());
  }

  @Override
  public Post getPost(String postID) {
    return postService.getPost(Long.parseLong(postID));
  }

}
