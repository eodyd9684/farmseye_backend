package com.green.farmseye.stock.service;

import com.green.farmseye.stock.dto.StockDTO;

import java.util.List;

public interface StockService {
  //개체 조회
  public List<StockDTO> selectStock(String userId);

  //개체 등록
  public void insertStock(StockDTO stockDTO);

  //개체 수정
  public void updateStock(StockDTO stockDTO);

  //개체 삭제
  public void deleteStock(int stockNum);
}
