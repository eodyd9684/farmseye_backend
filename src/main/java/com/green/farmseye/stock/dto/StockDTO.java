package com.green.farmseye.stock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StockDTO {
  private int stockNum;
  private int individualNum;  //개체수
  private int warehousing;    //입고수
  private int shipment;       //출하수
  private Float stockWeight;  //개체(닭) 무게
  private int deathStock;     //폐사 수
  private LocalDateTime regDate;
  private String userId;
}
