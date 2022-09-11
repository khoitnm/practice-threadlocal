package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_01_wait_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WaitStuckController {
  private final WaitStuckLv01Async waitStuckLv01Async;
  private static final String REQUEST_PATH = "/async/spawn-children/wait-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      @RequestParam(value = "lv01children", defaultValue = "5") int lv01Children,
      @RequestParam(value = "lv02children", defaultValue = "5") int lv02Children,
      @RequestParam(value = "lv03sleep", defaultValue = "100") int lv03sleep) {
    log.info("API {} is running...", REQUEST_PATH);
    waitStuckLv01Async.spawnChildren(lv01Children, lv02Children, lv03sleep);
    return "finished";
  }
}
