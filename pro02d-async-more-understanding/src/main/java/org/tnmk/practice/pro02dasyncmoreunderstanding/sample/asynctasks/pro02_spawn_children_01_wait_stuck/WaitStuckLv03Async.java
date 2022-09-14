package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_01_wait_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitStuckLv03Async {
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> async(int lv02Index, int lv03Index, int sleep) {
    String description = ProcessLogger.summary(this, lv02Index, lv03Index);
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
