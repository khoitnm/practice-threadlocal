package org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro01_spawn_children_01_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Wait3LvsNoStuckController {
  private final Wait3LvsNoStuckLv01Async wait3LvsNoStuckLv01Async;
  private static final String REQUEST_PATH = "/custom-async/spawn-children/wait-3lvs-no-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "requestIndex", required = false) String requestIndex,
      @RequestParam(value = "lv02Count", defaultValue = "11") int lv02Count,
      @RequestParam(value = "lv03Count", defaultValue = "2") int lv03Count,
      @RequestParam(value = "lv03Sleep", defaultValue = "100") int lv03sleep) throws ExecutionException, InterruptedException {
    if (requestIndex != null) {
      MDC.put("requestIndex", requestIndex);
    }
    log.info("API {} is running...", REQUEST_PATH);
    Future<String> future = wait3LvsNoStuckLv01Async.spawnChildren(lv02Count, lv03Count, lv03sleep);
    future.get();
    return "finished";
  }
}
