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
  private Float airQuality;
  private Float illumi;
  private LocalDateTime timestamp;
}
