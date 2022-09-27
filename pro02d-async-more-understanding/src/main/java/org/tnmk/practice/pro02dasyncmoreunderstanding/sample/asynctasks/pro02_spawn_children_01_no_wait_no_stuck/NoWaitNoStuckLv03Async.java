package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_01_no_wait_no_stuck;

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
public class NoWaitNoStuckLv03Async {
  //private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> async(int sleep) {
    String description = ProcessLogger.summary(this, null);
    ThreadLogger.log(description, Thread.currentThread());
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv03: " + sleep);
  }
}
