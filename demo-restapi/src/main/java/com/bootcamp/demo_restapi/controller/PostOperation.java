package com.bootcamp.demo_restapi.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.bootcamp.demo_restapi.model.Post;

public interface PostOperation {
  @GetMapping(value = "/posts")
  List<Post> getPosts();

  @GetMapping(value = "/posts/{postID}")
  Post getPost(@PathVariable String postID);

  // @PostMapping(value = "/create/post") // CREATE -> 0 to 1
  // @ResponseStatus(HttpStatus.CREATED)
  // Post createNewUser(@PathVariable String userID , @RequestBody NewPost newPost);


}
