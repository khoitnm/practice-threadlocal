package org.tnmk.practice.pro02casyncjoin.sample.parallelitems;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.util.Map;
import java.util.stream.IntStream;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ParallelItemsProcessing {
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  public void processItemsConcurrently(int itemsCount) {
    MDC.put("triggeredDateTime", Instant.now().toString());
    logger.info("Start parallelStream");

    Map<String, String> originalMdcContext = MDC.getCopyOfContextMap();
    IntStream.range(0, itemsCount).parallel().forEach((item) -> {
      MDC.setContextMap(originalMdcContext);
      try {
        processItem(item);
      } finally {
        MDC.clear();
      }
    });
    // Sometimes a concurrent thread is the same as parent thread.
    // In that case, we should reset the original context after running all concurrent threads.
    MDC.setContextMap(originalMdcContext);
    logger.info("Finish parallelStream");
  }

  private void processItem(int item) {
    MDC.put("asyncNanoTime", "" + System.nanoTime());
    logger.info("ParallelStreamItem " + item);
  }
}
