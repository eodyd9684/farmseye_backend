package com.green.farmseye.user.service;

import com.green.farmseye.user.dto.CustomUserDetails;
import com.green.farmseye.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername 메서드 실행!");
    UserDTO dto = userService.getUserForLogin(username);

    if(dto == null){
      log.info("=======일치하는 아이디 없음==========");
      //401 상태코드 반환
      //로그인 시도 하려는 회원의 아이디가 없으면 더이상 로그인 검증을 진행하지 않고 멈춤
      throw new UsernameNotFoundException("없는 아이디 : " + username);
    }

    //조회한 로그인 정보를 UserDetails 인터페이스를 상속한 CustomUserDetails 클래스에 저장하여 리턴.
    //리턴된 UserDetails 객체를 AuthenticationManager가 전달받아 로그인 검증을 실행.
    return new CustomUserDetails(dto);
  }
}
