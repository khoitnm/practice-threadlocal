package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_01_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ProcessLogger;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ThreadLogger;
import org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.AsyncSupport;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsNoStuckLv02Async {
  private final Wait3LvsNoStuckLv03Async wait3LvsNoStuckLv03Async;

  @Async(AsyncSupport.DECORATED_FORK_JOIN_POOL)
  public CompletableFuture<String> spawnChildren(final int lv02Index, int childrenCount, int lv03Sleep) {
    String description = ProcessLogger.summary(this, lv02Index);
    ThreadLogger.log(description, Thread.currentThread());

    CompletableFuture<?>[] futures = IntStream.range(0, childrenCount)
        .mapToObj(lv03Index -> {
          log.info(description + ": Start adding waitStuckLv03Async[" + lv03Index + "]");
          return wait3LvsNoStuckLv03Async.async(lv02Index, lv03Index, lv03Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv02[" + lv02Index + "] finished");
  }
}
