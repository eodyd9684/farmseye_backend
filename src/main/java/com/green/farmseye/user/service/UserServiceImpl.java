package com.green.farmseye.user.service;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;
import com.green.farmseye.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserMapper userMapper;

  //로그인 하려는 회원 정보 조회

  @Override
  public UserDTO getUserForLogin(String userId) {
    return userMapper.getUserForLogin(userId);
  }

  //회원 등록 기능
  @Override
  public void join(UserDTO userDTO) {
    userMapper.join(userDTO);
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


  //회원 등록 시 중복 확인 기능
  @Override
  public List<UserDTO> duplicateCheckUser() {
    return userMapper.duplicateCheckUser();
  }

  //회원 정보 수정 시 중복 확인
  @Override
  public UserDTO isUsable(String userId) {
    return userMapper.isUsable(userId);
  }

  //회원 탈퇴 기능
  @Override
  public boolean deactivateUser(String userId, String tokenUserId) {
    if (!userId.equals(tokenUserId)) {
      throw new AccessDeniedException("본인만 탈퇴할 수 있습니다.");
    }

    int result = userMapper.deactivateUser(userId);
    return result == 1;
  }

  @Override
  public void uploadImg(String originalName, String storedPath) {
    userMapper.uploadImg(originalName, storedPath);
  }

  @Override
  public String getUserImagePath(String userId) {
    return userMapper.findStoredPathByUserId(userId);
  }


}
