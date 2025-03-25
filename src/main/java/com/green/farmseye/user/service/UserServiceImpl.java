package com.green.farmseye.user.service;

import com.green.farmseye.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserMapper userMapper;
}
