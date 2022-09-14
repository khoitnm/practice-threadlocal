package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro01_no_spawn_01_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoSpawnNoStuckAsync {

  private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @Async
  public CompletableFuture<String> async(int asyncIndex, int sleep) {
    String processTitle = String.format("%s[%s]", this.getClass().getSimpleName(), asyncIndex);
    ThreadLogger.logSummary(processTitle + " sleeping " + sleep, applicationTaskExecutor);
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(processTitle + " ended!", asyncIndex);
    return CompletableFuture.completedFuture(processTitle);
  }
}
