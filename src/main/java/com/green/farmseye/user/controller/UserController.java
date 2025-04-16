package com.green.farmseye.user.controller;

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
  public void insertUser(UserDTO userDTO
                        , @RequestParam (name = "mainImg", required = true) MultipartFile mainImg
  ){
    //첨부파일(이미지) 업로드
    String mainAttachedFileName = uploadUtil.fileUpload(mainImg); //첨부된파일명은 fileUpload() 메서드에서 만들어짐

    //USER 테이블에 데이터 INSERT
     userService.join(userDTO);

    UserImgDTO userImg = new UserImgDTO();
    userImg.setOriginFileName(mainImg.getOriginalFilename());
    userImg.setAttachedFileName(mainAttachedFileName);
    userImg.setUserId(userDTO.getUserId());

    //imgList를 userDTO에 넣는 코드
    userDTO.setImgList(userImg);

    //USER_IMG 테이블에 이미지 정보 INSERT
    userService.insertImgs(userDTO.getImgList());
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
  @PutMapping("/{userId}")
  public void updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") String userId){
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
  @GetMapping("/{userId}")
  public ResponseEntity<?> isUsable(@PathVariable("userId") String userId){
    List<UserDTO> userList = userService.isUsable(userId);
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(userList);
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







}
