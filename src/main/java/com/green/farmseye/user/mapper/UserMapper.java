package com.green.farmseye.user.mapper;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
  //로그인 하려는 회원 정보 조회
  public UserDTO getUserForLogin(String userId);

  //회원 등록 쿼리
  public void join(UserDTO userDTO);

  //회원 이미지 등록 쿼리
  public void insertImgs(UserImgDTO userImgDTO);

  //회원 정보 조회 쿼리
  public List<UserDTO> selectUser();

  //회원 정보 삭제 쿼리
  public void deleteUser(String userId);

  //회원 정보 수정 쿼리
  public void updateUser(UserDTO userDTO);

  //회원 등록 시 중복 확인 기능
  public List<UserDTO> duplicateCheckUser();

  //회원 정보 수정 시 중복 확인 쿼리
  public UserDTO isUsable(String userId);

  //회원 탈퇴 쿼리
  public int deactivateUser(String userId);


}
