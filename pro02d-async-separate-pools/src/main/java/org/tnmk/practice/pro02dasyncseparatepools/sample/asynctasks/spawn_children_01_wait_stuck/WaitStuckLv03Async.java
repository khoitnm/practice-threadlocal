package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.spawn_children_01_wait_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitStuckLv03Async {
  @Async
  public CompletableFuture<String> async(int lv02Index, int lv03Index, Thread lv01Thread, Thread lv02Thread, int sleep) {
    Thread lv03Thread = Thread.currentThread();
    log.info("Lv03Async[{}-{}]: lv01 {} - lv02 {} - lv03 {}: sleeping {}...", lv02Index, lv03Index, lv01Thread.getName(), lv02Thread.getName(),
        lv03Thread.getName(), sleep);
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Lv03Async[{}-{}]: lv01 {} - lv02 {} - lv03 {}: finished", lv02Index, lv03Index, lv01Thread.getName(), lv02Thread.getName(),
        lv03Thread.getName());
    return CompletableFuture.completedFuture("Lv03: " + sleep);
  }
}
