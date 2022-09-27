package org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro01_spawn_children_02_wait_3lvs_no_stuck_with_failure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncseparatepools.common.ProcessLogger;
import org.tnmk.practice.pro02easyncseparatepools.common.ThreadLogger;
import org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.AsyncSupport;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsNoStuckLv02WithFailureAsync {
  private final Wait3LvsNoStuckLv03WithFailureAsync wait3LvsNoStuckLv03WithFailureAsync;

  @Async(AsyncSupport.EXECUTOR_BEAN_NAME)
  public CompletableFuture<String> spawnChildren(final int lv02Index, int childrenCount, int lv03Sleep) {
    String description = ProcessLogger.summary(this, lv02Index);
    ThreadLogger.log(description, Thread.currentThread());

    CompletableFuture<?>[] futures = IntStream.range(0, childrenCount)
        .mapToObj(lv03Index -> {
          log.info(description + ": Start adding waitStuckLv03Async[" + lv03Index + "]");
          return wait3LvsNoStuckLv03WithFailureAsync.async(lv02Index, lv03Index, lv03Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv02[" + lv02Index + "] finished");
  }
}
