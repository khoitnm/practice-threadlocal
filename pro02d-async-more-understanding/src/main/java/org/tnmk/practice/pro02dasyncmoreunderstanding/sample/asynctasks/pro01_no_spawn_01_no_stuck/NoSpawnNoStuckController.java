package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro01_no_spawn_01_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NoSpawnNoStuckController {

  private static final String REQUEST_PATH = "/async/spawn-children/no-spwan-no-stuck";
  private final NoSpawnNoStuckAsync noSpawnNoStuckAsync;
  //private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      @RequestParam(value = "childrenCount", defaultValue = "5") int childrenCount,
      @RequestParam(value = "sleep", defaultValue = "100") int sleep) {
    log.info("API {} is running...", REQUEST_PATH);
    CompletableFuture<?>[] futures = IntStream.range(0, childrenCount)
        .mapToObj(asyncIndex -> {
          String processTitle = String.format("%s[%s] before starting", this.getClass().getSimpleName(), asyncIndex);
          ThreadLogger.log(processTitle, Thread.currentThread());
          return noSpawnNoStuckAsync.async(asyncIndex, sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();
    return "finished";
  }
}
