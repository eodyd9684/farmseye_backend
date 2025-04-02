package com.green.farmseye.environment.service;

import com.green.farmseye.environment.mapper.EnvironmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {
  private final EnvironmentMapper environmentMapper;
}
