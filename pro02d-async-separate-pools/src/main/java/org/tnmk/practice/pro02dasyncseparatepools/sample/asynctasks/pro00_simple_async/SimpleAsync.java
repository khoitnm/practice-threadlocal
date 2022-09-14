package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.pro00_simple_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncseparatepools.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAsync {

  private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @Async
  public CompletableFuture<String> async(int sleep) {
    String processTitle = String.format("%s[%s]", this.getClass().getSimpleName(), null);
    ThreadLogger.logSummary(processTitle + " sleeping " + sleep, applicationTaskExecutor);
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(processTitle + " ended!");
    return CompletableFuture.completedFuture(processTitle);
  }
}
