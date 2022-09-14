package org.tnmk.practice.pro02easyncseparatepools.sample.asynctasks.pro02_spawn_children_01_wait_stuck;

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
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "lv02Count", defaultValue = "10") int lv02Count,
      @RequestParam(value = "lv03Count", defaultValue = "5") int lv03Count,
      @RequestParam(value = "lv03sleep", defaultValue = "100") int lv03sleep) {
    log.info("API {} is running...", REQUEST_PATH);
    waitStuckLv01Async.spawnChildren(lv02Count, lv03Count, lv03sleep);
    return "finished";
  }
}
