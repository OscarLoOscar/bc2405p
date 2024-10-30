package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.model.CommentPlaceHolder;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserPlaceHolder;

public interface JPHService {
  List<UserPlaceHolder> getUserRawdata();

  List<PostPlaceHolder> getPostRawdata();

  List<CommentPlaceHolder> getCommentRawdata();

}
