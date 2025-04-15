package com.green.farmseye.stock.mapper;

import com.green.farmseye.stock.dto.StockDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper {
  //개체 조회
  public List<StockDTO> selectStock();

  //개체 등록
  public void insertStock(StockDTO stockDTO);

  //개체 수정
  public void updateStock(StockDTO stockDTO);
}
