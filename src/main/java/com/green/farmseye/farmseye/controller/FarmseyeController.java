package com.green.farmseye.farmseye.controller;

import com.green.farmseye.farmseye.service.FarmseyeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/farms")
@RequiredArgsConstructor
public class FarmseyeController {
  private final FarmseyeService farmseyeService;
}
