package com.bootcamp.bc_forum.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.service.UserService;
import com.bootcamp.bc_forum.util.UrlManager;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UrlManager urlManager;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.endpoint.users}")
  private String endpoint;

  @Override
  public List<UserPlaceHolder> getRawdata() {
    return List.of(restTemplate.getForObject(urlManager.getUrl(endpoint),
        UserPlaceHolder[].class));
  }

}