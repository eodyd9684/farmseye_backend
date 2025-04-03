package com.green.farmseye.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDTO {
  private String userId;
  private String userPw;
  private String userName;
  private int userAge;
  private String userTel;
  private String userAddr;
  private LocalDateTime regDate;
  private String isUsing;
  private String userRoll;

  private UserImgDTO imgList;
}
