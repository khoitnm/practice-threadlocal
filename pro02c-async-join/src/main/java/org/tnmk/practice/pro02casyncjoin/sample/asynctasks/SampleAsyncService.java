package org.tnmk.practice.pro02casyncjoin.sample.asynctasks;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.CompletableFuture;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SampleAsyncService {
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  @Async
  public CompletableFuture<Void> asyncWriteLog(int item) {
    //This will be run in [lTaskExecutor-3] and able to get MDC value.
    MDC.put("asyncNanoTime", "" + System.nanoTime());
    logger.info("Sample Async Service " + item);
    return CompletableFuture.completedFuture(null);
  }
}
