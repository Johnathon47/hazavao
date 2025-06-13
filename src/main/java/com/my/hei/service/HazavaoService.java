package com.my.hei.service;

import org.springframework.stereotype.Service;

@Service
public class HazavaoService {
  public String getDefinition(String teny) {
    return "Hello " + teny;
  }
}
