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
  public static final int MAX_ITEM = 3;
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  public void processItemsConcurrently(int itemsCount) {
    MDC.put("triggeredDateTime", Instant.now().toString());
    logger.info("parallelStream: Start");
    if (itemsCount > MAX_ITEM) {
      throw new IllegalArgumentException("parallelStream: item must not be greater than " + MAX_ITEM);
    }

    Map<String, String> originalMdcContext = MDC.getCopyOfContextMap();
    IntStream.range(0, itemsCount).parallel().forEach((item) -> {
      MDC.setContextMap(originalMdcContext);
      try {
        processItem(item);
      } finally {
        MDC.clear();
      }
    });

    // All parallel processes will be joined here by default.
    // It means this method will wait for all parallel processes to be finished before continuing.

    // Sometimes a concurrent thread is the same as parent thread.
    // In that case, we should reset the original context after running all concurrent threads.
    MDC.setContextMap(originalMdcContext);
    logger.info("parallelStream: end");
  }

  private void processItem(int item) {
    MDC.put("asyncNanoTime", "" + System.nanoTime());
    logger.info("parallelStream: item " + item);
  }
}
