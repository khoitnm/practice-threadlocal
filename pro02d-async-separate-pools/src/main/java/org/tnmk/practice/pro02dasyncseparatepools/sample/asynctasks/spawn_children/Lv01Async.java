package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Lv01Async {
  private final Lv02Async lv02Async;

  @Async
  public CompletableFuture<String> spawnChildren(int lv01ChildThreads, int lv02ChildThreads, int lv03Sleep) {
    CompletableFuture<?>[] futures = IntStream.range(0, lv01ChildThreads)
        .mapToObj(i -> lv02Async.spawnChildren(lv02ChildThreads, lv03Sleep))
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    return CompletableFuture.completedFuture("Lv01 finished");
  }
}
