package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_02_wait_2lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Wait2LvsNoStuckController {
  private final Wait2LvsNoStuckLv01Async wait2LvsNoStuckLv01Async;
  private static final String REQUEST_PATH = "/async/spawn-children/wait-2lvs-no-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "requestIndex", defaultValue = "0") String requestIndex,
      @RequestParam(value = "lv02Count", defaultValue = "25") int lv02Count,
      @RequestParam(value = "lv02Sleep", defaultValue = "5000") int lv02sleep) {
    MDC.put("requestIndex", requestIndex);
    log.info("API {} is running...", REQUEST_PATH);
    wait2LvsNoStuckLv01Async.spawnChildren(lv02Count, lv02sleep);
    MDC.remove("requestIndex");
    return "finished";
  }
}
