package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.UserCommentDTO;

public interface UserService {
   List<UserEntity> saveAll(List<UserEntity> userEntities);

   List<UserEntity> getAll();

   UserEntity getUserByID(Long id);

   List<UserCommentDTO> getUserCommentByID(Long id);
}
