package com.my.hei.endpoint.rest.controller.health;

import com.my.hei.service.HazavaoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HazavaoController {
  private final HazavaoService hazavaoService;

  public HazavaoController(HazavaoService hazavaoService) {
    this.hazavaoService = hazavaoService;
  }

  @GetMapping("/hazavao")
  public String hazavao(@RequestParam String teny) {
    return hazavaoService.getDefinition(teny);
  }
}
