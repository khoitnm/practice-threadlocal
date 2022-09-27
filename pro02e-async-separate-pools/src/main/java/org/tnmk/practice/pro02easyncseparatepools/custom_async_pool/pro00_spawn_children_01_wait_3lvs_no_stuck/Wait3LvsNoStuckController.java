package org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro00_spawn_children_01_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Wait3LvsNoStuckController {
  private final Wait3LvsNoStuckLv01Async wait3LvsNoStuckLv01Async;
  private static final String REQUEST_PATH = "/custom-async/spawn-children/wait-3lvs-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "lv02Count", defaultValue = "11") int lv02Count,
      @RequestParam(value = "lv03Count", defaultValue = "2") int lv03Count,
      @RequestParam(value = "lv03Sleep", defaultValue = "100") int lv03sleep) {
    log.info("API {} is running...", REQUEST_PATH);
    wait3LvsNoStuckLv01Async.spawnChildren(lv02Count, lv03Count, lv03sleep);
    return "finished";
  }
}
