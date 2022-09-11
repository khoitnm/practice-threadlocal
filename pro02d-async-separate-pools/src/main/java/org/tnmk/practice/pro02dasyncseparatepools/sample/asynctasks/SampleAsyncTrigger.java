package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SampleAsyncTrigger {
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  private SampleAsyncService sampleAsyncService;

  public void start(int asyncTasksCount) {
    MDC.put("triggeredDateTime", Instant.now().toString());
    logger.info("SampleAsyncTrigger: start");

    CompletableFuture<?>[] futures = IntStream.range(0, asyncTasksCount)
        .mapToObj(i -> sampleAsyncService.asyncWriteLog(i))
        .toArray(CompletableFuture[]::new);

    logger.info("SampleAsyncTrigger: wait for all async to finish");
    CompletableFuture.allOf(futures).join();
    logger.info("SampleAsyncTrigger: finish all async");
  }
}
