package com.bootcamp.demo_restapi.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bootcamp.demo_restapi.entity.PostEntity;
import com.bootcamp.demo_restapi.infra.ApiUtil;
import com.bootcamp.demo_restapi.infra.Scheme;
import com.bootcamp.demo_restapi.model.Post;
import com.bootcamp.demo_restapi.model.PostDTO;
import com.bootcamp.demo_restapi.model.mapper.Mapper;
import com.bootcamp.demo_restapi.repository.PostRepository;
import com.bootcamp.demo_restapi.service.PostService;

@Service
public class PostServiceHolder implements PostService {
  @Value("${api.url.postEndpoint}")
  private String postEndpoint;

  @Autowired
  private ApiUtil apiUtil;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private Mapper mapper;

  @Override
  public Post[] getPosts() {
    Post[] posts = restTemplate
        .getForObject(apiUtil.getUrl(Scheme.HTTP, postEndpoint), Post[].class);

    if (postRepository.findAll().size() == 0) {
      Arrays.asList(posts).stream()//
          .forEach(post -> {
            PostEntity postEntity = mapper.map(post);
            postRepository.save(postEntity);
          });
    } else {
      System.out.println("Already have data");
    }

    return restTemplate.getForObject(apiUtil.getUrl(Scheme.HTTP, postEndpoint),
        Post[].class);

  }

  @Override
  public Post getPost(Long postID) {
    return Arrays.stream(this.getPosts())//
        .filter(post -> post.getId() == postID)//
        .findFirst()//
        .get();
  }

  @Override
  public List<PostDTO> getAllPostsByUserId(Long userID) {
    return Arrays.stream(this.getPosts())//
        .filter(post -> post.getUserId().equals(userID))//
        .map(post -> mapper.mapToDTO(post))//
        .collect(Collectors.toList());
  }

}
