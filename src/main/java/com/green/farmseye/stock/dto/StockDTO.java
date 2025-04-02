package com.green.farmseye.stock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDTO {
  private int stockNum;
  private Float feedWeight;
  private float stockWeight;
}
