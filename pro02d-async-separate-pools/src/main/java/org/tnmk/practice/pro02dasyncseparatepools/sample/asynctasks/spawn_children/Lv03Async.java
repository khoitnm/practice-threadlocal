package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Lv03Async {
  @SneakyThrows
  @Async
  public CompletableFuture<String> async(int sleep) {
    log.info("Lv03Async: {}", sleep);
    Thread.sleep(sleep);
    return CompletableFuture.completedFuture("Lv03: " + sleep);
  }
}
