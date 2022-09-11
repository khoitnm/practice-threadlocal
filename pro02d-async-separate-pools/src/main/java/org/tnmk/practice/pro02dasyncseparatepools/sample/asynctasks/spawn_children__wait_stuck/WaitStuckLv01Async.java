package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children__wait_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitStuckLv01Async {
  private final WaitStuckLv02Async waitStuckLv02Async;

  @Async
  public CompletableFuture<String> spawnChildren(int lv01ChildThreads, int lv02ChildThreads, int lv03Sleep) {
    log.info("Lv01Async is running...");

    Thread lv01Thread = Thread.currentThread();
    CompletableFuture<?>[] futures = IntStream.range(0, lv01ChildThreads)
        .mapToObj(i -> waitStuckLv02Async.spawnChildren(lv01Thread, lv02ChildThreads, lv03Sleep))
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();
    log.info("Lv01Async is finished");
    return CompletableFuture.completedFuture("Lv01 finished");
  }
}
