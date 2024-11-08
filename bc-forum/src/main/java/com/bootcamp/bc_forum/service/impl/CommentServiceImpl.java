package com.bootcamp.bc_forum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.bc_forum.model.Mapper;
import com.bootcamp.bc_forum.repository.CommentRepository;
import com.bootcamp.bc_forum.service.CommentService;
import com.bootcamp.bc_forum.service.JPHService;

@Service
public class CommentServiceImpl implements CommentService{


  @Autowired
  private JPHService jphService;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private Mapper mapper;



}
