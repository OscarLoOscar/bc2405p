package com.bootcamp.demo_restapi.service;

import org.springframework.stereotype.Service;

import com.bootcamp.demo_restapi.model.UserPostDTO;

@Service
public interface DTOService {

  UserPostDTO getUserPost(Long userID , Long postID);
}