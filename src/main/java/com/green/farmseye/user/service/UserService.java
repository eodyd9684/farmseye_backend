package com.green.farmseye.user.service;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;

import java.util.List;

public interface UserService {

  //회원 등록 기능
  public void insertUser(UserDTO userDTO);

  //회원 이미지 등록 기능
  public void insertImgs(UserImgDTO userImgDTO);

  //회원 정보 조회 기능
  public List<UserDTO> selectUser();
}
