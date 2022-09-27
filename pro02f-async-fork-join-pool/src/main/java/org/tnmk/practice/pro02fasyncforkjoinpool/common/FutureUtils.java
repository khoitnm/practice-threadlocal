package org.tnmk.practice.pro02fasyncforkjoinpool.common;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class FutureUtils {
  public static void waitForAll(Future<?>[] futures) {
    for (Future<?> future : futures) {
      try {
        future.get();
      } catch (ExecutionException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
