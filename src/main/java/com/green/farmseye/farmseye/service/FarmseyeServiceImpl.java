package com.green.farmseye.farmseye.service;

import com.green.farmseye.farmseye.mapper.FarmseyeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmseyeServiceImpl implements FarmseyeService{
  private final FarmseyeMapper farmseyeMapper;
}
