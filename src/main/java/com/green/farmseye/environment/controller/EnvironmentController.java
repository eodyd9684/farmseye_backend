package com.green.farmseye.environment.controller;

import com.green.farmseye.environment.service.EnvironmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class EnvironmentController {
  private final EnvironmentService farmseyeService;
}
