package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_01_wait_stuck;

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
public class WaitStuckLv02Async {
  private final WaitStuckLv03Async waitStuckLv03Async;
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(final int lv02Index, int childrenCount, int lv03Sleep) {
    String description = ProcessLogger.summary(this, lv02Index);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);

    CompletableFuture<?>[] futures = IntStream.range(0, childrenCount)
        .mapToObj(lv03Index -> {
          log.info(description + ": Start adding waitStuckLv03Async[" + lv03Index + "]");
          return waitStuckLv03Async.async(lv02Index, lv03Index, lv03Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv02[" + lv02Index + "] finished");
  }
}
