package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.pro02_spawn_children_02_no_wait_no_stuck;

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
public class NoWaitNoStuckLv02Async {
  private final NoWaitNoStuckLv03Async noWaitNoStuckLv03Async;
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(int childThreads, int lv03Sleep) {
    String description = ProcessLogger.summary(this, null);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);

    Thread lv02Thread = Thread.currentThread();
    IntStream.range(0, childThreads)
        .forEach(i -> noWaitNoStuckLv03Async.async(lv03Sleep));

    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv02 finished");
  }
}
