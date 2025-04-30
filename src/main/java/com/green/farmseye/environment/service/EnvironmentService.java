package com.green.farmseye.environment.service;

import com.green.farmseye.environment.dto.EnvironmentDTO;
import com.green.farmseye.environment.dto.EnvironmentMinMaxDTO;

import java.util.List;

public interface EnvironmentService {
  //farmseye_env 테이블 목록 조회 기능
  public List<EnvironmentDTO> selectEnv(String userId);

  //farmseye_env 최신 데이터 1개 조회 기능
  public EnvironmentDTO selectNowEnv(String userId);

  //farmseye_env_minmax 테이블 목록 조회
  public EnvironmentMinMaxDTO selectEnvMinMax(String userId);

  //minmax 테이블 수정
  public void updateMinMax(EnvironmentMinMaxDTO environmentMinMaxDTO);

}
