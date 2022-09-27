package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.MdcKeys;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ParallelStreamController {

  private static final String REQUEST_PATH = "/parallel/simple";
  private final ParallelStreamService parallelStreamService;
  //private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      @RequestParam(value = "itemsCount", defaultValue = "2") int itemsCount,
      @RequestParam(value = "sleep", defaultValue = "100") int sleep) throws ExecutionException, InterruptedException {
    String processTitle = String.format("%s[%s] %s before starting", this.getClass().getSimpleName(), itemsCount, REQUEST_PATH);
    ThreadLogger.log(processTitle, Thread.currentThread());
    parallelStreamService.processItemsConcurrently(itemsCount, sleep);
    return "" + itemsCount;
  }
}
