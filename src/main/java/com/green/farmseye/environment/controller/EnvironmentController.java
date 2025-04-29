package com.green.farmseye.environment.controller;

import com.green.farmseye.environment.dto.EnvironmentDTO;
import com.green.farmseye.environment.dto.EnvironmentMinMaxDTO;
import com.green.farmseye.environment.service.EnvironmentService;
import com.green.farmseye.user.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farms")
public class EnvironmentController {
  private final EnvironmentService environmentService;

  @Autowired
  public EnvironmentController(EnvironmentService environmentService){
    this.environmentService = environmentService;
  }


  @GetMapping("")
  private List<EnvironmentDTO> selectEnv(@AuthenticationPrincipal CustomUserDetails customUserDetails){
    String userId = customUserDetails.getUsername();
    System.out.println("@@@@@@@@@@@@@@" + userId);
    return environmentService.selectEnv(userId);
  };

  @GetMapping("/{userId}")
  public EnvironmentMinMaxDTO selectEnvMinMax(@PathVariable("userId") String userId){
    return environmentService.selectEnvMinMax(userId);
  }

  @PutMapping("/{userId}")
  public void updateMinMax(@RequestBody EnvironmentMinMaxDTO environmentMinMaxDTO, @PathVariable("userId") String userId){
    environmentMinMaxDTO.setUserId(userId);
    environmentService.updateMinMax(environmentMinMaxDTO);
  }

}
