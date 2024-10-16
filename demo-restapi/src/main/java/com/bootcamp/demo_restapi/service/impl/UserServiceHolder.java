package com.bootcamp.demo_restapi.service.impl;

import java.util.Arrays;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_restapi.entity.UserEntity;
import com.bootcamp.demo_restapi.exception.ErrorCode;
import com.bootcamp.demo_restapi.exception.UserNotExistException;
import com.bootcamp.demo_restapi.infra.ApiUtil;
import com.bootcamp.demo_restapi.infra.Scheme;
import com.bootcamp.demo_restapi.model.User;
import com.bootcamp.demo_restapi.model.UserRequest;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.repository.UserRepository;
import com.bootcamp.demo_restapi.service.UserService;

@Service
public class UserServiceHolder implements UserService {

  @Value("${api.url.endpoint.user}")
  private String userEndpoint;

  @Autowired
  private ApiUtil apiUtil;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private Mapper mapper;

  @Override
  public User[] getUsers() {
    User[] users = restTemplate
        .getForObject(apiUtil.getUrl(Scheme.HTTP, userEndpoint), User[].class);

    if (userRepository.findAll().isEmpty()) {
      Arrays.asList(users).stream()//
          .forEach(user -> {
            UserEntity userEntity = mapper.map(user);
            userRepository.save(userEntity);
          });
    } else {
      System.out.println("Already have data");
    }

    return restTemplate.getForObject(apiUtil.getUrl(Scheme.HTTP, userEndpoint),
        User[].class);
  }

  @Override
  public User getUser(Long userID) {
    return Arrays.stream(this.getUsers())//
        .filter(user -> user.getId().equals(Integer.valueOf(userID.toString())))//
        .findFirst()//
        .get();
  }

  @Override
  public UserEntity createNewUser(String name, String email, String phone) {
    return userRepository.save(UserEntity.builder()//
        .name(name)//
        .email(email)//
        .phone(phone)//
        .build());
  }

  @Override
  public User modifyUser(String userID, UserRequest userRequest) {
    Optional<UserEntity> targetUser =
        userRepository.findById(Long.parseLong(userID));

    if (targetUser.isPresent()) {
      targetUser.get().setName(userRequest.getName());
      targetUser.get().setEmail(userRequest.getEmail());
      targetUser.get().setPhone(userRequest.getPhone());
      userRepository.save(targetUser.get());
    }
    throw new UserNotExistException(ErrorCode.USER_NOT_FIND_EXPECTION);
  }

  @Override
  public User updateEmail(String userID, String email) {
    Optional<UserEntity> targetUser =
        userRepository.findById(Long.parseLong(userID));

    UserEntity existUser = targetUser.get();
    existUser.setEmail(email);
    userRepository.save(existUser);
    return mapper.map(existUser);
  }

  @Override
  public User updateMobile(String userID, String mobile) {
    Optional<UserEntity> targetUser =
        userRepository.findById(Long.parseLong(userID));
    UserEntity existUser = targetUser.get();
    existUser.setPhone(mobile);
    userRepository.save(existUser);
    return mapper.map(existUser);

  }

}
