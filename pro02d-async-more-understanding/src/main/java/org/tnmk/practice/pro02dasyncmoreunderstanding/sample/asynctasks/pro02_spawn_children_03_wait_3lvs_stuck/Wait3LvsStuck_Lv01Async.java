package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_3lvs_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsStuck_Lv01Async {
  private final Wait3LvsStuck_Lv02Async wait3LvsStuckLv02Async;
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
