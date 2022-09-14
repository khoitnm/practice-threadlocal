package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro01_no_spawn_02_wait;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoSpawnWaitLv01Async {
  private final NoSpawnWaitLv02Async noSpawnWaitLv02Async;
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(int lv02Count, int lv02Sleep) {

    String description = ProcessLogger.summary(this, null);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);

    CompletableFuture<?>[] futures = IntStream.range(0, lv02Count)
        .mapToObj(lv02Index -> {
          log.info(description + ": Start adding lv02[" + lv02Index + "]");
          return noSpawnWaitLv02Async.async(lv02Index, lv02Sleep);
        })
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv01 finished");
  }
}
