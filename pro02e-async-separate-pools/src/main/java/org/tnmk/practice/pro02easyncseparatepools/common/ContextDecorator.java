package org.tnmk.practice.pro02easyncseparatepools.common;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * This class helps to copy SecurityContext to another thread when we use {@link Async}.
 * Guideline: https://moelholm.com/blog/2017/07/24/spring-43-using-a-taskdecorator-to-copy-mdc-data-to-async-threads
 * Just a note that it cannot copy to another thread when we use pure Java code to create another thread.
 * For example:
 * <pre>
 * someList.parallelStream()                                  // this parallelStream will execute each item in a separate thread
 *  .map(item -> authenthicationService.getAuthentication())  // this will fail because there's no SecurityContext
 * </pre>
 */
@Component
public class ContextDecorator implements TaskDecorator {

  @Override
  public Runnable decorate(Runnable runnable) {
    Map<String, String> mdcFromParentThread = MDC.getCopyOfContextMap();
    return () -> {
      // Child thread begins...
      try {
        MDC.setContextMap(mdcFromParentThread); // copy mdc from parent
        runnable.run();
      } finally {
        for (String mdcKey : mdcFromParentThread.keySet()) {
          MDC.remove(mdcKey);
        }
      }
      // Child thread end
    };
  }
}
