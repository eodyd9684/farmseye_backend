package com.green.farmseye.user.controller;

import com.green.farmseye.user.dto.UserDTO;
import com.green.farmseye.user.dto.UserImgDTO;
import com.green.farmseye.user.service.UserService;
import com.green.farmseye.util.UploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
  private final UserService userService;
  private final UploadUtil uploadUtil;

  //회원 등록 api
  @PostMapping("")
  public void insertUser(UserDTO userDTO
                        , @RequestParam (name = "mainImg", required = true) MultipartFile mainImg
  ){
    //첨부파일(이미지) 업로드
    String mainAttachedFileName = uploadUtil.fileUpload(mainImg); //첨부된파일명은 fileUpload() 메서드에서 만들어짐

    //USER 테이블에 데이터 INSERT
    userService.insertUser(userDTO);

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
            .body("서버에서 오류발생");
  }

  //회원 삭제 api
  @DeleteMapping("/{userId}")
  public void deleteUser(@PathVariable("userId") String userId){
    userService.deleteUser(userId);
  }

  //회원 수정 api
  @PostMapping("/{userId}")
  public void updateUser(@RequestBody UserDTO userDTO, @PathVariable("userId") String userId){
  userService.updateUser(userDTO);
  }
}
