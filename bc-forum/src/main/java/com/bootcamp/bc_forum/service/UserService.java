package com.bootcamp.bc_forum.service;

import java.util.List;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.UserCommentDTO;
import com.bootcamp.bc_forum.model.UserDTO;

public interface UserService {
   List<UserEntity> saveAll(List<UserEntity> userEntities);

   List<UserEntity> getAll();

   UserEntity getUserByID(Long id);

   List<UserCommentDTO> getUserCommentByID(Long id);

   UserDTO modifyMobileNumber(Long userID, String newMobile  );

  List<UserDTO> getDataFromRedis();
}
