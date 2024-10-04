package com.bootcamp.demo_restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.PostDTO;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserPostDTO;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.service.DTOService;
import com.bootcamp.demo_restapi.service.PostService;
import com.bootcamp.demo_restapi.service.UserService;

@Service

public class DTOServiceHolder implements DTOService {
  @Autowired
  private PostService postService;

  @Autowired
  private UserService userService;

  @Autowired
  private Mapper mapper;

  @Override
  public UserPostDTO getUserPost(Long userID, Long postID) {

    User user = userService.getUser(userID);
    UserPostDTO userPostDTO = mapper.mapUserToUserPostDTO(user);

    if (postID == 0) {
      List<PostDTO> posts = postService.getAllPostsByUserId(userID);
      userPostDTO.setPostDTO(posts);
    } else {
      List<PostDTO> postDTOs = Optional.ofNullable(postService.getPost(postID))//
          .filter(post -> post.getUserId().equals(userID))//
          .map(post -> List.of(mapper.mapToDTO(post)))//
          .orElse(List.of());
      userPostDTO.setPostDTO(postDTOs);
    }
    return userPostDTO;
  }
}