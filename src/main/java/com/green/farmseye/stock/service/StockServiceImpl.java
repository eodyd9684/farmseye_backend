package com.green.farmseye.stock.service;

import com.green.farmseye.stock.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
  private final StockMapper stockMapper;
}
