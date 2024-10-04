package com.bootcamp.demo_restapi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserPostDTO {
  private Long userID;
  private String username;
  private String useremail;
  private List<PostDTO> postDTO;

}
