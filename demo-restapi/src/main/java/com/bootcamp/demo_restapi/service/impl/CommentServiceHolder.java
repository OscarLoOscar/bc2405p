package com.bootcamp.demo_restapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo_restapi.entity.CommentEntity;
import com.bootcamp.demo_restapi.infra.ApiUtil;
import com.bootcamp.demo_restapi.infra.Scheme;
import com.bootcamp.demo_restapi.model.Comment;
import com.bootcamp.demo_restapi.model.CommentDTO;
import com.bootcamp.demo_restapi.repository.CommentRepository;
import com.bootcamp.demo_restapi.service.CommentService;

@Service
public class CommentServiceHolder implements CommentService {
  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private ApiUtil apiUtil;

  @Value("${api.url.endpoint.comment}")
  private String commentEndpoint;

  @Override
  public Comment[] getComment() {
    Comment[] commentArr = restTemplate.getForObject(
        apiUtil.getUrl(Scheme.HTTPS, commentEndpoint), Comment[].class);

    if (commentRepository.findAll().isEmpty()) {// .length()== 0
      List<CommentEntity> commentEntityList = List.of(commentArr).stream()//
          // .map(comment->mapper.map(comment))
          .map(comment -> {
            CommentEntity commentEntity = CommentEntity.builder()//
                .postId(comment.getPostId())//
                .id(comment.getId())//
                .name(comment.getName())//
                .email(comment.getEmail())//
                .body(comment.getBody())//
                .build();
            return commentEntity;
          })//
          .collect(Collectors.toList());

      commentRepository.saveAll(commentEntityList);
    }
    return commentArr;
  }

  @Override
  public Comment getComment(Long commentID) {
    return List.of(this.getComment()).stream()//
        .filter(c -> c.getId().equals(commentID)).findFirst()//
        .get();
  }

  @Override
  public List<CommentDTO> getAllCommentsByUserId(Long userID) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException(
        "Unimplemented method 'getAllCommentsByUserId'");
  }

}
