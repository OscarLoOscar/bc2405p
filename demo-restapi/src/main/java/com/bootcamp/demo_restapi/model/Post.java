package com.bootcamp.demo_restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
  private Long userId;
  private Long id;
  private String title;
  private String body;
}
