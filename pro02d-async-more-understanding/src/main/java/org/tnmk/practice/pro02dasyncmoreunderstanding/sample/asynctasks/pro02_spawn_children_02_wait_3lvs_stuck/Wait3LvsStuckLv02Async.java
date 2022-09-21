package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_02_wait_3lvs_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsStuckLv02Async {
  private final Wait3LvsStuckLv03Async wait3LvsStuckLv03Async;
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(final int lv02Index, int childrenCount, int lv03Sleep) {
    String description = ProcessLogger.summary(this, lv02Index);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);

    CompletableFuture<?>[] futures = IntStream.range(0, childrenCount)
        .mapToObj(lv03Index -> {
          log.info(description + ": Start adding waitStuckLv03Async[" + lv03Index + "]");
          return wait3LvsStuckLv03Async.async(lv02Index, lv03Index, lv03Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv02[" + lv02Index + "] finished");
  }
}
