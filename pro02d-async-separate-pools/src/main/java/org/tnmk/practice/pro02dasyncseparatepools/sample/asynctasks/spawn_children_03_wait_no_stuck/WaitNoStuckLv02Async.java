package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_03_wait_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncseparatepools.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncseparatepools.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitNoStuckLv02Async {
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> async(int lv02Index, int sleep) {
    String description = ProcessLogger.summary(this, lv02Index);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv03: " + sleep);
  }
}
