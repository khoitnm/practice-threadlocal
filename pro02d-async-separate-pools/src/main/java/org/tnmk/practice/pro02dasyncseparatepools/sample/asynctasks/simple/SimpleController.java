package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.simple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimpleController {
  private final SimpleService simpleService;

  @GetMapping("/sync/simple")
  public String syncSimple() {
    return "finished";
  }

  @GetMapping("/async/simple")
  public String asyncSimple() {
    simpleService.async();
    return "finished";
  }
}
