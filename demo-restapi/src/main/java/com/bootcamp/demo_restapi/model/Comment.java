package com.bootcamp.demo_restapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Comment {
  private Long postId;
  private Long id;
  private String name;
  private String email;
  private String body;

}
