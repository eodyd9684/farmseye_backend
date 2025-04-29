package com.green.farmseye.user.service;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;

import java.util.List;

public interface UserService {
  //로그인 하려는 회원 정보 조회
  public UserDTO getUserForLogin(String userId);

  //회원 등록 기능
  public void join(UserDTO userDTO);

  //회원 이미지 등록 기능
  public void insertImgs(UserImgDTO userImgDTO);

  //회원 정보 조회 기능
  public List<UserDTO> selectUser();

  //회원 정보 삭제 기능
  public void deleteUser(String userId);

  //회원 정보 수정 기능
  public void updateUser(UserDTO userDTO);

  //회원 등록 시 중복 확인 기능
  public List<UserDTO> duplicateCheckUser();

  //회원 정보 수정 시 중복 확인 기능
  public UserDTO isUsable(String userId);

  //회원 탈퇴 기능
  public boolean deactivateUser(String userId, String tokenUserId);

  //이미지 업로드
  public void uploadImg(String originalName, String storedPath, String userId);

  //이미지 경로 받아오기
  public String getUserImagePath(String userId);

  //이미지 테이블 정보 조회
  public UserImgDTO getUserImg(String userId);

  //이미지 삭제
  public int deleteUserImagePath(String userId);
}
