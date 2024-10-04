package com.bootcamp.demo_restapi.service;

import java.util.List;

import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.PostDTO;

public interface PostService {
    Post[] getPosts();

    Post getPost(Long postID);

    List<PostDTO> getAllPostsByUserId(Long userID);
}
