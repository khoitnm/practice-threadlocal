package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_3lvs_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Wait3LvsStuck_Controller {
  private final Wait3LvsStuck_Lv01Async wait3LvsStuckLv01Async;
  private static final String REQUEST_PATH = "/async/spawn-children/wait-3lvs-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "lv02Count", defaultValue = "11") int lv02Count,
      @RequestParam(value = "lv03Count", defaultValue = "2") int lv03Count,
      @RequestParam(value = "lv03Sleep", defaultValue = "100") int lv03sleep) throws ExecutionException, InterruptedException {
    log.info("API {} is running...", REQUEST_PATH);
    Future<String> future = wait3LvsStuckLv01Async.spawnChildren(lv02Count, lv03Count, lv03sleep);
    future.get();
    return "finished";
  }
}
