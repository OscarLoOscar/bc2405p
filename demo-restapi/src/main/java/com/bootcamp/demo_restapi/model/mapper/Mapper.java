package com.bootcamp.demo_restapi.model.mapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bootcamp.demo_restapi.entity.PostEntity;
import com.bootcamp.demo_restapi.entity.UserEntity;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.PostDTO;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserPostDTO;
import com.bootcamp.demo_restapi.service.PostService;

@Component
public class Mapper {
  public UserEntity map(User user) {
    return UserEntity.builder()//
        .name(user.getName())//
        .username(user.getUsername())//
        .email(user.getEmail())//
        .phone(user.getPhone())//
        .website(user.getWebsite())//
        .dummy(String.valueOf(""))//
        .build();
  }

  public PostEntity map(Post post) {
    return PostEntity.builder()//
        .postId(post.getId())//
        .userId(post.getUserId())//
        .title(post.getTitle())//
        .body(post.getBody())
        .build();
  }

  public PostDTO mapToDTO(Post post) {
    return PostDTO.builder()//
        .id(post.getId())//
        .userId(post.getUserId())//
        .title(post.getTitle())//
        .body(post.getBody())
        .build();
  }

  public UserPostDTO mapUserToUserPostDTO(User user) {
    return UserPostDTO.builder()//
        .userID(Long.valueOf(user.getId()))//
        .username(user.getUsername())//
        .useremail(user.getEmail())//
        .build();
  }
}
