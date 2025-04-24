package com.green.farmseye.user.controller;

import com.green.farmseye.user.dto.CustomUserDetails;
import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;
import com.green.farmseye.user.service.UserService;
import com.green.farmseye.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;
  private final UploadUtil uploadUtil;
  private final PasswordEncoder passwordEncoder;

  //회원 등록 api
  @PostMapping("")
  public ResponseEntity<?> insertUser(
          @ModelAttribute UserDTO userDTO,
          @RequestParam(name = "mainImg", required = false) MultipartFile mainImg
  ) {
    try {
      log.info("회원가입 기능 실행");

      // 비밀번호 암호화
      String encoded_pw = passwordEncoder.encode(userDTO.getUserPw());
      userDTO.setUserPw(encoded_pw);

      // 사용자 정보 DB 저장
      userService.join(userDTO);

      // 이미지 처리
      if (mainImg != null && !mainImg.isEmpty()) {
        String mainAttachedFileName = uploadUtil.fileUpload(mainImg);

        UserImgDTO userImg = new UserImgDTO();
        userImg.setOriginFileName(mainImg.getOriginalFilename());
        userImg.setAttachedFileName(mainAttachedFileName);
        userImg.setUserId(userDTO.getUserId());

        userDTO.setImgList(userImg);
        userService.insertImgs(userDTO.getImgList());
      }

      return ResponseEntity.ok(userDTO);

    } catch (Exception e) {
      log.error("회원가입 중 오류 발생", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원가입 실패");
    }
  }


  //회원 조회 api
  @GetMapping("")
  public ResponseEntity<?> selectUser(){
    List<UserDTO> userList = userService.selectUser();
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userList);
  }

  //회원 삭제 api
  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable("userId") String userId){
    userService.deleteUser(userId);
  }

  //회원 수정 api
  @PreAuthorize("isAuthenticated()")
  @PutMapping("")
  public void updateUser(@RequestBody UserDTO userDTO){
    //userDTO.setUserId(userId); //url에서 받은 userId를 강제 주입 (보안상 안전을 위해)

    //비밀번호 변경 했을 경우에만 비번 암호화를 진행
    if(!userDTO.getUserPw().equals("")){
      String encodedPw = passwordEncoder.encode(userDTO.getUserPw());
      userDTO.setUserPw(encodedPw);
    }
    userService.updateUser(userDTO);
  }

  //회원 등록 시 중복 확인
  @GetMapping("/check")
  public ResponseEntity<?> duplicateCheckUser(){
    List<UserDTO> userList = userService.duplicateCheckUser();
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userList);
  }

  //회원 정보 수정 시 중복 확인
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/isUsable")
  public ResponseEntity<?> isUsable(@AuthenticationPrincipal CustomUserDetails customUserDetails){
    String userId = customUserDetails.getUsername();
    UserDTO userDTO = userService.isUsable(userId);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userDTO);
  }


  //비밀번호 암호화 연습 메서드
  @GetMapping("/test")
  public void testEncode(){
    String str1 = "java";
    String str2 = "java";
    String pw = "asdf1234";
    String encodedPw = passwordEncoder.encode(pw);
    passwordEncoder.matches("asdf1234", encodedPw);

    //암호화
    String encoded_Str1 =  passwordEncoder.encode(str1);
    String encoded_Str2 =  passwordEncoder.encode(str2);

    System.out.println("암호화 된 str1 = " + encoded_Str1);
    System.out.println("암호화 된 str2 = " + encoded_Str2);
    System.out.println("암호화 된 pw = " + encodedPw);

    //matches(원본 문자열, 암호화된 문자열) -> true, false
    boolean result1 = passwordEncoder.matches(str1, encoded_Str1);
    boolean result2 = passwordEncoder.matches("python", encoded_Str1);
    System.out.println("result1 = " + result1);
    System.out.println("result2 = " + result2);
  };

  //회원가입
  @PostMapping("/join")
  public ResponseEntity<?> join(@RequestBody UserDTO userDTO){
    log.info("회원가입 기능 실행");

    //비밀번호 암호화
    String encoded_pw = passwordEncoder.encode(userDTO.getUserPw());
    userDTO.setUserPw(encoded_pw);

    userService.join(userDTO);

    return ResponseEntity.status(HttpStatus.OK).body(userDTO);
  }

  //회원 탈퇴 기능
  @DeleteMapping("/deactivate")
  public ResponseEntity<String> deactivateUser(@AuthenticationPrincipal UserDetails userDetails){
    String userId = userDetails.getUsername(); //jwt 기반 인증 사용자 ID

    boolean result = userService.deactivateUser(userId, userId);

    if (result) {
      return ResponseEntity.ok("회원 탈퇴 완료");
    } else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 탈퇴된 계정이거나 존재하지 않습니다.");
    }

  }






}
