package com.bootcamp.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.bc_forum.model.CommentPlaceHolder;
import com.bootcamp.bc_forum.model.PostPlaceHolder;
import com.bootcamp.bc_forum.model.UserPlaceHolder;
import com.bootcamp.bc_forum.service.JPHService;
import com.bootcamp.bc_forum.util.UrlManager;

@Service
public class JPHServiceImpl implements JPHService {
  @Autowired
  private UrlManager urlManager;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.jsonplaceholder.endpoint.users}")
  private String userEndpoint;

  @Value("${api.jsonplaceholder.endpoint.posts}")
  private String postEndpoint;

  @Value("${api.jsonplaceholder.endpoint.comments}")
  private String commentEndpoint;

  @Override
  public List<UserPlaceHolder> getUserRawdata() {
    return List.of(restTemplate.getForObject(urlManager.getUrl(userEndpoint),
        UserPlaceHolder[].class));
  }


  @Override
  public List<PostPlaceHolder> getPostRawdata() {
    return List.of(restTemplate.getForObject(urlManager.getUrl(postEndpoint),
        PostPlaceHolder[].class));
  }

  @Override
  public List<CommentPlaceHolder> getCommentRawdata() {
    return Arrays.asList(restTemplate.getForObject(urlManager.getUrl(commentEndpoint),
    CommentPlaceHolder[].class));
  }

}
