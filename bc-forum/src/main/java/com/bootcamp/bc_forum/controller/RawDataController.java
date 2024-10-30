package com.bootcamp.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.bootcamp.bc_forum.model.CommentPlaceHolder;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserPlaceHolder;

public interface RawDataController {

  List<CommentPlaceHolder> getCommentRawdata();

  List<PostPlaceHolder> getPostRawdata();

  @GetMapping("/users")
  List<UserPlaceHolder> getUserRawdata();


}
