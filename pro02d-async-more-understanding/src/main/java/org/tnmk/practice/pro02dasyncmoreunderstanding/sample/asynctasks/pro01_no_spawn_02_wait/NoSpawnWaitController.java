package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro01_no_spawn_02_wait;

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
public class NoSpawnWaitController {
  private final NoSpawnWaitLv01Async noSpawnWaitLv01Async;
  private static final String REQUEST_PATH = "/async/spawn-children/no-spawn-wait";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "lv02Count", defaultValue = "10") int lv02Count,
      @RequestParam(value = "lv02Sleep", defaultValue = "100") int lv02sleep) throws ExecutionException, InterruptedException {
    log.info("API {} is running...", REQUEST_PATH);
    Future<String> future = noSpawnWaitLv01Async.spawnChildren(lv02Count, lv02sleep);
    String result = future.get();
    return result;
  }
}
