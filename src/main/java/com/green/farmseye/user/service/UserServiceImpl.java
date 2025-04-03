package com.green.farmseye.user.service;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;
import com.green.farmseye.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserMapper userMapper;

  //회원 등록 기능
  @Override
  public void insertUser(UserDTO userDTO) {

  }

  //회원 이미지 등록 기능
  @Override
  public void insertImgs(UserImgDTO userImgDTO) {
  userMapper.insertImgs(userImgDTO);
  }

  //회원 조회 기능
  @Override
  public List<UserDTO> selectUser() {
    return userMapper.selectUser();
  }

  //회원 삭제 기능
  @Override
  public void deleteUser(String userId) {
  userMapper.deleteUser(userId);
  }

  //회원 수정 기능
  @Override
  public void updateUser(UserDTO userDTO) {
  userMapper.updateUser(userDTO);
  }
}
