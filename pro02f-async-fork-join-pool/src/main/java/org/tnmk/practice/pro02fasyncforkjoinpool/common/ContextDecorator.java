package org.tnmk.practice.pro02fasyncforkjoinpool.common;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

/**
 * @deprecated This class only works with default ThreadPool of @Async (which is {@link ThreadPoolTaskExecutor})
 * It doesn't work with {@link ForkJoinPool}: https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool
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
@Deprecated
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
