package com.bootcamp.bc_forum.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class UserCommentDTO {
  private Long id;
  private String username;

  @Builder.Default
  private List<CommentDTO> comments = new ArrayList<>();


}
