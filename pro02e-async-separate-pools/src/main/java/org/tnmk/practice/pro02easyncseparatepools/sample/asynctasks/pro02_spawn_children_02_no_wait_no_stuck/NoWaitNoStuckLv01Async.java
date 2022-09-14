package org.tnmk.practice.pro02easyncseparatepools.sample.asynctasks.pro02_spawn_children_02_no_wait_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncseparatepools.common.ProcessLogger;
import org.tnmk.practice.pro02easyncseparatepools.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoWaitNoStuckLv01Async {
  private final NoWaitNoStuckLv02Async noWaitNoStuckLv02Async;
  private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

  @Async
  public CompletableFuture<String> spawnChildren(int lv01ChildThreads, int lv02ChildThreads, int lv03Sleep) {
    String description = ProcessLogger.summary(this, null);
    ThreadLogger.logSummary(description, threadPoolTaskExecutor);

    IntStream.range(0, lv01ChildThreads)
        .forEach(i -> noWaitNoStuckLv02Async.spawnChildren(lv02ChildThreads, lv03Sleep))
    ;

    //    CompletableFuture.allOf(futures).join();
    log.info(description + " finished");
    return CompletableFuture.completedFuture("Lv01 finished");
  }
}
