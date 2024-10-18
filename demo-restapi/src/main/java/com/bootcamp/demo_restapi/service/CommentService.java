package com.bootcamp.demo_restapi.service;

import java.util.List;
import com.bootcamp.demo_restapi.model.Comment;
import com.bootcamp.demo_restapi.model.CommentDTO;

public interface CommentService {
    Comment[] getComment();

    Comment getComment(Long commentID);

    List<CommentDTO> getAllCommentsByUserId(Long userID);
}
