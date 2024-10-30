package com.bootcamp.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.model.Mapper;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.repository.PostRepository;
import com.bootcamp.bc_forum.repository.UserRepository;
import com.bootcamp.bc_forum.service.JPHService;
import com.bootcamp.bc_forum.service.PostService;
import com.bootcamp.bc_forum.util.UrlManager;

@Service
public class PostServiceImpl implements PostService {


  @Autowired
  private JPHService jphService;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private Mapper mapper;

  private List<PostPlaceHolder> getPostPlaceHolders() {
    return jphService.getPostRawdata();
  }

}

