package com.bootcamp.bc_forum.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_forum.entity.AddressEntity;
import com.bootcamp.bc_forum.entity.CompanyEntity;
import com.bootcamp.bc_forum.entity.GeoEntity;
import com.bootcamp.bc_forum.entity.UserEntity;
import com.bootcamp.bc_forum.model.Mapper;
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
    return this.getAll().stream().filter(user -> id.equals(user.getId()))
        .findFirst().get();
  }


}
