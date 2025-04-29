package com.green.farmseye.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserImgDTO {
  private int imgCode;
  private String originalName;
  private String storedPath;
  private String userId;
}
