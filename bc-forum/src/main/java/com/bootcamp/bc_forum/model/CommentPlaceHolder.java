package com.bootcamp.bc_forum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommentPlaceHolder {
  private Integer postId;
  private Integer id;
  private String name;
  private String email;
  private String body;

}
