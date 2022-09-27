package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_00_wait_3lvs_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ProcessLogger;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsStuckLv03Async {
  //private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> async(int lv02Index, int lv03Index, int sleep) {
    String description = ProcessLogger.summary(this, lv02Index, lv03Index);
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
