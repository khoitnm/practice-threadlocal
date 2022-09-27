package org.tnmk.practice.pro02fasyncforkjoinpool.custom_async_pool.pro00_simple_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.MdcKeys;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ThreadLogger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SimpleAsyncController {

  private static final String REQUEST_PATH = "/async/simple";
  private final SimpleAsync simpleAsync;
  //private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      @RequestParam(value = "requestIndex", required = false, defaultValue = "") Integer requestIndex,
      @RequestParam(value = "sleep", defaultValue = "100") int sleep) throws ExecutionException, InterruptedException {
    MDC.put(MdcKeys.MDC_KEY_REQUEST_INDEX, "" + requestIndex);
    String processTitle;
    if (requestIndex != null) {
      processTitle = String.format("%s[%s] %s before starting", this.getClass().getSimpleName(), requestIndex, REQUEST_PATH);
    } else {
      processTitle = String.format("%s %s before starting", this.getClass().getSimpleName(), REQUEST_PATH);
    }
    ThreadLogger.log(processTitle, Thread.currentThread());
    Future<String> future = simpleAsync.async(sleep);
    String result = future.get();
    MDC.remove(MdcKeys.MDC_KEY_REQUEST_INDEX);
    return result;
  }
}
