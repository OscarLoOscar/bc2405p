package com.bootcamp.bc_forum.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.CommentDTO;
import com.bootcamp.bc_forum.model.Mapper;
import com.bootcamp.bc_forum.model.UserCommentDTO;
import com.bootcamp.bc_forum.model.UserDTO;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.repository.UserRepository;
import com.bootcamp.bc_forum.service.JPHService;
import com.bootcamp.bc_forum.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private JPHService jphService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Mapper mapper;

  private List<UserPlaceHolder> getUserRawdata() {
    return jphService.getUserRawdata();
  }

  @Override
  public List<UserEntity> saveAll(List<UserEntity> userentities) {
    return userRepository.saveAll(userentities);
  }

  @Override
  public List<UserEntity> getAll() {
    return userRepository.findAll();
  }

  @Override
  public UserEntity getUserByID(Long id) {
    return this.getAll().stream()//
        .filter(user -> id.equals(user.getId()))//
        .findFirst().get();
  }

  @Override
  public List<UserCommentDTO> getUserCommentByID(Long id) {
    // 01/11/2024 target
    // raw : post1[[comment1] , [comment2]] , post2[[comment3] , [comment4]]
    // expect result : user[comment1,comment2,comment3,comment4]
    // List<List<CommentDTO>> convert to List<Comment>
    // flatMap()
    // Input to Output 1 input -> 1 output 1 input -> n outputs (flattened)
    // Question : how to use flatMap() ??????

    UserEntity user = this.getAll().stream()//
        .filter(userEntity -> id.equals(userEntity.getId())) // == primitive use only , class use equals
        .findFirst()//
        .get();

    UserDTO userDTO = mapper.map(user);

    List<CommentDTO> targetCommentDTO = userDTO.getPosts().stream()//
        .flatMap(postDTO -> postDTO.getComments().stream())//
        .collect(Collectors.toList());

    List<UserCommentDTO> result = new ArrayList<>();
    UserCommentDTO target = UserCommentDTO.builder()//
        .id(userDTO.getId())//
        .username(userDTO.getUsername())//
        .comments(targetCommentDTO).build();

    result.add(target);
    return result;//

  }


}
