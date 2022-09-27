package org.tnmk.practice.pro02fasyncforkjoinpool.common;

public class ProcessLogger {
  public static String summary(Object serviceInstance, int... asyncIndexes) {
    String result = serviceInstance.getClass().getSimpleName();
    if (asyncIndexes != null) {
      for (int i = 0; i < asyncIndexes.length; i++) {
        result += "[" + asyncIndexes[i] + "]";
      }
    }
    return result;
  }
}
