package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StuckController {
  private final StuckLv01Async stuckLv01Async;

  @GetMapping("/async/spawn-children/stuck")
  public String asyncSpawnChildren(
      @RequestParam(value = "lv01children", defaultValue = "10") int lv01Children,
      @RequestParam(value = "lv02children", defaultValue = "10") int lv02Children,
      @RequestParam(value = "lv03sleep", defaultValue = "100") int lv03sleep) {
    log.info("API /async/spawn-children is running...");
    stuckLv01Async.spawnChildren(lv01Children, lv02Children, lv03sleep);
    return "finished";
  }
}
