package com.green.farmseye.stock.service;

import com.green.farmseye.stock.dto.StockDTO;
import com.green.farmseye.stock.mapper.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
  private final StockMapper stockMapper;

  @Override
  public List<StockDTO> selectStock(String userId) {
    return stockMapper.selectStock(userId);
  }

  @Override
  public void insertStock(StockDTO stockDTO) {
    stockMapper.insertStock(stockDTO);
  }

  @Override
  public void updateStock(StockDTO stockDTO) {
    stockMapper.updateStock(stockDTO);
  }

  @Override
  public void deleteStock(int stockNum) {
    stockMapper.deleteStock(stockNum);
  }
}
