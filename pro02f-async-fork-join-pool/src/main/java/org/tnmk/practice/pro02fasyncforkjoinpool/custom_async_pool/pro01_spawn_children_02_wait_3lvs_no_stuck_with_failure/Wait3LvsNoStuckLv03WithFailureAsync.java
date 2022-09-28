package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_02_wait_3lvs_no_stuck_with_failure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ProcessLogger;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ThreadLogger;
import org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.AsyncSupport;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsNoStuckLv03WithFailureAsync {

  @Async(AsyncSupport.DECORATED_FORK_JOIN_POOL)
  public CompletableFuture<String> async(int lv02Index, int lv03Index, int sleep) {
    String description = ProcessLogger.summary(this, lv02Index, lv03Index);
    ThreadLogger.log(description, Thread.currentThread());
    if ((double) lv03Index % 2d == 0) {
      throw new RuntimeException(description + ": Test failure case");
    }

    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv03: " + sleep);
  }
}
