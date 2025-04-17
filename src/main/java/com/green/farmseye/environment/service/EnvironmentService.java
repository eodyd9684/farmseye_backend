package com.green.farmseye.environment.service;

import com.green.farmseye.environment.dto.EnvironmentDTO;

import java.util.List;

public interface EnvironmentService {
  //farmseye_env 테이블 목록 조회 기능
  public List<EnvironmentDTO> selectEnv(String userId);

}
