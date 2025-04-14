package com.green.farmseye.user.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.farmseye.user.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;

@Slf4j
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public LoginFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil){
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;

    setFilterProcessesUrl("/user/login");  //로그인 요청 url 변경
    setUsernameParameter("userId");   //변경하지 않으면 아이디는 username으로 전달
    setPasswordParameter("userPw");   //변경하지 않으면 비번은 password로 전달
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    log.info("LoginFilter 클래스의 attemptAuthentication() 메서드 실행");

    //입력한 아이디 및 비번 받기
    UserDTO dto = new UserDTO();
    try{
      //전달되는 아이디 및 비번을 UserDTO에 저장하는 코드
      ObjectMapper objectMapper = new ObjectMapper();
      ServletInputStream inputStream = request.getInputStream();
      String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
      dto = objectMapper.readValue(messageBody, UserDTO.class);
    }catch (Exception e){
      throw new RuntimeException(e);
    }
    log.info("전달받은 아이디 : " + dto.getUserId());
    log.info("전달받은 비번 : " + dto.getUserPw());

    //우리가 입력한 아이디와 비밀번호를 데이터베이스에 저장한 정보와 일치하는지 검증하는 로직은
    //AuthenticationManager가 담당하기 때문에 전달받은 아이디와 비밀번호를 AuthenticationManager에 전달해줘야 한다.
    //이때 아이디와 비밀번호를 그냥 전달하는 것이 아니라 UsernamePasswordAuthenticationToken 객체에 실어 보낸다.
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getUserId(), dto.getUserPw(), null);

    //아이디와 비번을 담고 있는 authToken객체를 authenticationManager에 전달, authenticationManager는 로그인을 검증하는 기능을 함
    //로그인을 검증하는 방법 -> UserDetailsService의 loadUserByUsername 메서드를 호출하여 검증
    //loadUserByUsername() 메서드의 실행 결과로 로그인 유저의 정보를 authentication 객체에 담아 옴
    Authentication authentication = authenticationManager.authenticate(authToken);
    log.info("DB에서 로그인 가능 여부 확인 완료(UserDetailsService의 loadUserByUsername 메서드 정상 실행 됨). 만약 검증에 실패했다면 본 출력문 실행 안 됨");
    log.info("로그인 중인 유저 : " + authentication.getName());

    //로그인 유저의 정보가 담긴 authentication객체를 리턴하면 authentication객체가 session에 저장됨
    //세션에 저장하는 이유는 security의 권한 처리를 위해서는 세션에 로그인 정보가 있어야 되기 때문.
    return authentication;
  }

  //인증 성공시 자동 실행되는 메서드
  //인증된 유저에게 토큰을 발급하는 코드를 작성
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
    log.info("로그인 검증 성공");

    //토큰 생성을 위한 아이디 정보 추출
    String username = authResult.getName();

    //토큰 생성을 위한 권한 정보 추출
    Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
    Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
    GrantedAuthority auth = iterator.next();
    String role = auth.getAuthority();

    //토큰 생성
    String accessToken = jwtUtil.createJwt(username, role, (1000 * 60 * 30)); //1000 = 1초, 30분

    //생성한 토큰을 응답 헤더에 담아 클라이언트에 전달
    response.setHeader("Access-Control-Expose-Headers", "Authorization");
    response.setHeader("Authorization", "Bearer " + accessToken);
    response.setStatus(HttpStatus.OK.value()); //클라이언트에 200 응답
    
  }

  //인증 실패시 자동 실행되는 메서드
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    log.info("로그인 검증 실패");

    //로그인을 실패하는 401 상태코드 반환
    response.setStatus(401);
  }
}
