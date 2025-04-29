package com.green.farmseye.environment.mapper;

import com.green.farmseye.environment.dto.EnvironmentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnvironmentMapper {
  //farmseye_env 테이블 목록 조회 쿼리
  public List<EnvironmentDTO> selectEnv(String userId);

  //farmseye_env 최신 데이터 1개 조회 쿼리
  public EnvironmentDTO selectNowEnv(String userId);
}
