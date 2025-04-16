package com.green.farmseye.stock.controller;

import com.green.farmseye.stock.dto.StockDTO;
import com.green.farmseye.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {
  private final StockService stockService;

  //개체 조회
  @GetMapping("")
  public List<StockDTO> selectStock(){
    return stockService.selectStock();
  }

  //개체 등록
  @PostMapping("/join")
  public void insertStock(@RequestBody StockDTO stockDTO){
    stockDTO.setIndividualNum(stockDTO.getWarehousing());
    stockService.insertStock(stockDTO);
  }

  //개체 수정
  @PutMapping("/{stockNum}")
  public void updateStock(@RequestBody StockDTO stockDTO, @PathVariable("stockNum") int stockNum){
    stockDTO.setIndividualNum(stockDTO.getWarehousing() - stockDTO.getShipment() - stockDTO.getDeathStock());
    stockService.updateStock(stockDTO);
  }

  @DeleteMapping("/{stockNum}")
  public void deleteStock(@PathVariable("stockNum") int stockNum){
    stockService.deleteStock(stockNum);
  }
}
