package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_02_no_wait_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoWaitNoStuckController {
  private final NoWaitNoStuckLv01Async noWaitNoStuckLv01Async;

  @GetMapping("/async/spawn-children/no-wait-no-stuck")
  public String asyncSpawnChildren(
      @RequestParam(value = "lv01children", defaultValue = "10") int lv01Children,
      @RequestParam(value = "lv02children", defaultValue = "10") int lv02Children,
      @RequestParam(value = "lv03sleep", defaultValue = "100") int lv03sleep) {
    log.info("API /async/spawn-children is running...");
    noWaitNoStuckLv01Async.spawnChildren(lv01Children, lv02Children, lv03sleep);
    return "finished";
  }
}
