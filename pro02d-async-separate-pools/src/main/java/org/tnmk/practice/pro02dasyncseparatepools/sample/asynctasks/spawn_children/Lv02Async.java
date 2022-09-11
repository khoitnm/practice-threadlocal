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
public class Lv02Async {
  private final Lv03Async lv03Async;

  @Async
  public CompletableFuture<String> spawnChildren(int childThreads, int lv03Sleep) {
    CompletableFuture<?>[] futures = IntStream.range(0, childThreads)
        .mapToObj(i -> lv03Async.async(lv03Sleep))
        .toArray(CompletableFuture[]::new);

    CompletableFuture.allOf(futures).join();

    return CompletableFuture.completedFuture("Lv02 finished");
  }
}
