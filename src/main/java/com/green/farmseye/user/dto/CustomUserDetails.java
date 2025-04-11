package com.green.farmseye.user.dto;

import com.green.farmseye.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
  private final UserDTO userDTO;

  //계정의 권한정보를 리턴하는 메서드
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return userDTO.getUserRole();
      }
    });

    return collection;
  }

  //계정의 비밀번호를 리턴
  @Override
  public String getPassword() {
    return userDTO.getUserPw();
  }

  //계정의 아이디를 리턴
  @Override
  public String getUsername() {
    return userDTO.getUserId();
  }

  //만료되지 않은 계정인지 확인
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //잠기지 않은 계정인지 확인
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //자격증명이 만료되지 않았는지 확인
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //사용 가능한 상태의 계정인지 확인
  @Override
  public boolean isEnabled() {
    return true;
  }
}
