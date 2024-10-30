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
public class PostPlaceHolder {
  private Integer userId;
  private Integer id;
  private String title;
  private String body;

}
