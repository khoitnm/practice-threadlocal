package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro01_spawn_children_00_wait_3lvs_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ProcessLogger;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsStuckLv01Async {
  private final Wait3LvsStuckLv02Async wait3LvsStuckLv02Async;
  //private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(int lv02Count, int lv03Count, int lv03Sleep) {

    String description = ProcessLogger.summary(this, null);
    ThreadLogger.log(description, Thread.currentThread());

    CompletableFuture<?>[] futures = IntStream.range(0, lv02Count)
        .mapToObj(lv02Index -> {
          log.info(description + ": Start adding waitStuck_Lv02Async[" + lv02Index + "]");
          return wait3LvsStuckLv02Async.spawnChildren(lv02Index, lv03Count, lv03Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv01 finished");
  }
}
