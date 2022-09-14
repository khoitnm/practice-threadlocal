package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.pro02_spawn_children_03_wait_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WaitNoStuckController {
  private final WaitNoStuckLv01Async waitNoStuckLv01Async;
  private static final String REQUEST_PATH = "/async/spawn-children/wait-no-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "lv02Count", defaultValue = "10") int lv02Count,
      @RequestParam(value = "lv02Sleep", defaultValue = "100") int lv02sleep) {
    log.info("API {} is running...", REQUEST_PATH);
    waitNoStuckLv01Async.spawnChildren(lv02Count, lv02sleep);
    return "finished";
  }
}
