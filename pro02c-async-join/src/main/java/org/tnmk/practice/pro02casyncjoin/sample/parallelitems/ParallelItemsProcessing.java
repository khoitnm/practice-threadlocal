package org.tnmk.practice.pro02casyncjoin.sample.parallelitems;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ParallelItemsProcessing {
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  public void processItemsConcurrently(int itemsCount) {
    MDC.put("triggeredDateTime", Instant.now().toString());
    logger.info("Start creating items");
    List<String> items = generateList(itemsCount);

    Map<String, String> originalMdcContext = MDC.getCopyOfContextMap();
    items.parallelStream().forEach((item) -> {
      Thread childThread = Thread.currentThread();
      MDC.setContextMap(originalMdcContext);
      try {
        MDC.put("asyncNanoTime", "" + System.nanoTime());
        logger.info("Processing item " + item + MDC.getCopyOfContextMap());
      } finally {
        MDC.clear();
      }
    });
    // Sometimes a concurrent thread is the same as parent thread.
    // In that case, we should reset the original context after running all concurrent threads.
    MDC.setContextMap(originalMdcContext);
    logger.info("Finish creating items");
  }

  private List<String> generateList(int itemsCount) {
    List<String> list = new ArrayList<>(itemsCount);
    for (int i = 0; i < itemsCount; i++) {
      list.add("Item_" + i);
    }
    return list;
  }
}
