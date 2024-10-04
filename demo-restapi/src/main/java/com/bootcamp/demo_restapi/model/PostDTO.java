package com.bootcamp.demo_restapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PostDTO {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}
