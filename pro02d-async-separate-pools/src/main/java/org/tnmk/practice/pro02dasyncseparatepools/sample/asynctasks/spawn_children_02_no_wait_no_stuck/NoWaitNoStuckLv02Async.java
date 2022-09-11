package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_02_no_wait_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoWaitNoStuckLv02Async {
  private final NoWaitNoStuckLv03Async noWaitNoStuckLv03Async;

  @Async
  public CompletableFuture<String> spawnChildren(Thread lv01Thread, int childThreads, int lv03Sleep) {
    log.info("Lv02Async is running...");

    Thread lv02Thread = Thread.currentThread();
    IntStream.range(0, childThreads)
        .forEach(i -> noWaitNoStuckLv03Async.async(lv01Thread, lv02Thread, lv03Sleep));

    log.info("Lv02Async is finished");
    return CompletableFuture.completedFuture("Lv02 finished");
  }
}
