package org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro00_simple_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncseparatepools.common.MdcKeys;
import org.tnmk.practice.pro02easyncseparatepools.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAsync {

  //private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @Async
  public CompletableFuture<String> async(int sleep) {
    String requestIndex = MDC.get(MdcKeys.MDC_KEY_REQUEST_INDEX);
    String processTitle = String.format("%s[%s]", this.getClass().getSimpleName(), requestIndex);
    ThreadLogger.log(processTitle, Thread.currentThread());
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(processTitle + " ended!");
    return CompletableFuture.completedFuture(processTitle);
  }
}
