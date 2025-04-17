package com.green.farmseye.environment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EnvironmentDTO {
  private int eyeNum;
  private Float temp;
  private Float humi;
  private Float no2;
  private Float co2;
  private Float nh3;
  private Float h2s;
  private Float toluene;
  private Float illumi;
  private LocalDateTime timestamp;
  private String userId;
}
