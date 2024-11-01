package com.bootcamp.bc_forum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ModifyMobile {
  private String userID;
  private String newMobileNum;

}
