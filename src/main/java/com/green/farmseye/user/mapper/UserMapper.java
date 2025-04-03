package com.green.farmseye.user.mapper;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

  //회원 등록 쿼리
  public void insertUser(UserDTO userDTO);

  //회원 이미지 등록 쿼리
  public void insertImgs(UserImgDTO userImgDTO);

  //회원 정보 조회 쿼리
  public List<UserDTO> selectUser();
}
