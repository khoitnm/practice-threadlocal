package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_01_wait_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitStuckLv02Async {
  private final WaitStuckLv03Async waitStuckLv03Async;

  @Async
  public CompletableFuture<String> spawnChildren(int lv02Index, Thread lv01Thread, int childThreads, int lv03Sleep) {
    log.info("Lv02Async[{}] is running...", lv02Index);

    Thread lv02Thread = Thread.currentThread();
    CompletableFuture<?>[] futures = IntStream.range(0, childThreads)
        .mapToObj(lv03Index -> waitStuckLv03Async.async(lv02Index, lv03Index, lv01Thread, lv02Thread, lv03Sleep))
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    log.info("Lv02Async[{}] is finished", lv02Index);
    return CompletableFuture.completedFuture("Lv02[" + lv02Index + "] finished");
  }
}
