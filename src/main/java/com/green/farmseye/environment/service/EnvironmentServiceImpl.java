package com.green.farmseye.environment.service;

import com.green.farmseye.environment.dto.EnvironmentDTO;
import com.green.farmseye.environment.dto.EnvironmentMinMaxDTO;
import com.green.farmseye.environment.mapper.EnvironmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {
  private final EnvironmentMapper environmentMapper;

  @Override
  public List<EnvironmentDTO> selectEnv(String userId) {
    return environmentMapper.selectEnv(userId);
  }

  @Override
  public EnvironmentDTO selectNowEnv(String userId) {
    return environmentMapper.selectNowEnv(userId);
  }

  @Override
  public EnvironmentMinMaxDTO selectEnvMinMax(String userId) {
    return environmentMapper.selectEnvMinMax(userId);
  }

  @Override
  public void updateMinMax(EnvironmentMinMaxDTO environmentMinMaxDTO) {
    environmentMapper.updateMinMax(environmentMinMaxDTO);
  }
}
