package com.green.farmseye.stock.controller;

import com.green.farmseye.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class StockController {
  private final StockService stockService;
}
