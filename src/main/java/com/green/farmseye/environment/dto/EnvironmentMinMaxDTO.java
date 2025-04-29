package com.green.farmseye.environment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnvironmentMinMaxDTO {
  private int num;
  private Float minTem;
  private Float maxTem;
  private Float minHumi;
  private Float maxHumi;
  private Float minIllumi;
  private Float maxIllumi;
  private Float bouNo2;
  private Float danNo2;
  private Float bouCo2;
  private Float danCo2;
  private Float bouNh3;
  private Float danNh3;
  private Float bouH2s;
  private Float danH2s;
  private Float bouToluene;
  private Float danToluene;
  private String userId;
}
