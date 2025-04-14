package com.green.farmseye.environment.service;

import com.green.farmseye.environment.dto.EnvironmentDTO;
import com.green.farmseye.environment.mapper.EnvironmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {
  private final EnvironmentMapper environmentMapper;

  @Override
  public List<EnvironmentDTO> selectEnv() {
    return environmentMapper.selectEnv();
  }
}
