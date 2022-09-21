package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.lang.invoke.MethodHandles;
import java.util.stream.IntStream;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class ParallelStreamService {
  public static final int MAX_ITEM = 3;
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  public void processItemsConcurrently(int itemsCount, int sleep) {
    logger.info("parallelStream: Start");
    IntStream.range(0, itemsCount)
        .parallel()
        .forEach((itemIndex) -> {
          processItem(itemIndex, sleep);
        });

    // All parallel processes will be joined here by default.
    // It means this method will wait for all parallel processes to be finished before continuing.

    // Sometimes a concurrent thread is the same as parent thread.
    // In that case, we should reset the original context after running all concurrent threads.
    logger.info("parallelStream: end");
  }

  private void processItem(int itemIndex, int sleep) {
    String description = String.format("parallelStream[%s]", itemIndex);
    ThreadLogger.log(description + " starting...", Thread.currentThread());
    try {
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    logger.info(description + " ended");
  }
}
