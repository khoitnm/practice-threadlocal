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
public class WaitStuckLv02Async {
  private final WaitStuckLv03Async waitStuckLv03Async;

  @Async
  public CompletableFuture<String> spawnChildren(Thread lv01Thread, int childThreads, int lv03Sleep) {
    log.info("Lv02Async is running...");

    Thread lv02Thread = Thread.currentThread();
    CompletableFuture<?>[] futures = IntStream.range(0, childThreads)
        .mapToObj(i -> waitStuckLv03Async.async(lv01Thread, lv02Thread, lv03Sleep))
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    log.info("Lv02Async is finished");
    return CompletableFuture.completedFuture("Lv02 finished");
  }
}
