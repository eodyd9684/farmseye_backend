package com.green.farmseye.environment.controller;

import com.green.farmseye.environment.dto.EnvironmentDTO;
import com.green.farmseye.environment.service.EnvironmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class EnvironmentController {
  private final EnvironmentService environmentService;

  @GetMapping("")
  private List<EnvironmentDTO> selectEnv(){
    return environmentService.selectEnv();
  };

}
