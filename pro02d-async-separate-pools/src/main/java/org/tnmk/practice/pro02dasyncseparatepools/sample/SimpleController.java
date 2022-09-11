package org.tnmk.practice.pro02dasyncseparatepools.sample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.simple.SimpleService;
import org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children.Lv01Async;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimpleController {
  private final SimpleService simpleService;
  private final Lv01Async lv01Async;

  @GetMapping("/sync/simple")
  public String syncSimple() {
    return "finished";
  }

  @GetMapping("/async/simple")
  public String asyncSimple() {
    simpleService.async();
    return "finished";
  }

  @GetMapping("/async/spawn-children")
  public String asyncSpawnChildren(
      @RequestParam(value = "lv01children", defaultValue = "10") int lv01Children,
      @RequestParam(value = "lv02children", defaultValue = "10") int lv02Children,
      @RequestParam(value = "lv03sleep", defaultValue = "100") int lv03sleep) {
    log.info("API /async/spawn-children is running...");
    lv01Async.spawnChildren(lv01Children, lv02Children, lv03sleep);
    return "finished";
  }
}