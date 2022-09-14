package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.pro02_spawn_children_01_wait_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncseparatepools.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncseparatepools.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitStuckLv01Async {
  private final WaitStuckLv02Async waitStuckLv02Async;
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(int lv02Count, int lv03Count, int lv03Sleep) {

    String description = ProcessLogger.summary(this, null);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);

    CompletableFuture<?>[] futures = IntStream.range(0, lv02Count)
        .mapToObj(lv02Index -> {
          log.info(description + ": Start adding waitStuckLv02Async[" + lv02Index + "]");
          return waitStuckLv02Async.spawnChildren(lv02Index, lv03Count, lv03Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv01 finished");
  }
}
