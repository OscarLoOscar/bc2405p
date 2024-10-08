package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserRequest;

public interface PostOperation {
  @GetMapping(value = "/posts")
  List<Post> getPosts();

  @GetMapping(value = "/posts/{postID}")
  Post getPost(@PathVariable String postID);

  @PostMapping(value = "/create/post") // CREATE -> 0 to 1
  @ResponseStatus(HttpStatus.CREATED)
  Post createNewUser(@PathVariable String userID , @RequestBody NewPost newPost);

}
