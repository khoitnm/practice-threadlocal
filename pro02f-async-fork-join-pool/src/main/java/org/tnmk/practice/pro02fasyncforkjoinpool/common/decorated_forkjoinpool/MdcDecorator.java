package org.tnmk.practice.pro02fasyncforkjoinpool.common.decorated_forkjoinpool;

import org.slf4j.MDC;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicReference;

public class MdcDecorator implements TaskDecorator {

  @Override
  public Runnable decorate(Runnable task) {
    Map<String, String> newContext = MDC.getCopyOfContextMap();
    return () ->
    {
      Map<String, String> oldContext = copyParentContext(newContext);
      try {
        task.run();
      } finally {
        resetChildContext(oldContext);
      }
    };
  }

  @Override
  public Callable decorate(Callable task) {
    Map<String, String> newContext = MDC.getCopyOfContextMap();
    return () -> {
      Map<String, String> oldContext = copyParentContext(newContext);
      try {
        return task.call();
      } finally {
        resetChildContext(oldContext);
      }
    };
  }

  @Override
  public <T> ForkJoinTask<T> decorate(ForkJoinTask<T> task) {
    Map<String, String> parentContext = MDC.getCopyOfContextMap();
    return new ForkJoinTask<T>() {
      private static final long serialVersionUID = 1L;
      /**
       * If non-null, overrides the value returned by the underlying task.
       */
      private final AtomicReference<T> override = new AtomicReference<>();

      @Override
      public T getRawResult() {
        T result = override.get();
        if (result != null) {
          return result;
        }
        return task.getRawResult();
      }

      @Override
      protected void setRawResult(T value) {
        override.set(value);
      }

      @Override
      protected boolean exec() {
        // According to ForkJoinTask.fork() "it is a usage error to fork a task more than once unless it has completed
        // and been reinitialized". We therefore assume that this method does not have to be thread-safe.
        Map<String, String> originalChildContext = copyParentContext(parentContext);
        try {
          task.invoke();
          return true;
        } finally {
          resetChildContext(originalChildContext);
        }
      }
    };
  }

  /**
   * Invoked before running a task.
   *
   * @param parentContext the new MDC context
   * @return the old MDC context
   */
  private static Map<String, String> copyParentContext(Map<String, String> parentContext) {
    Map<String, String> originalContextFromChildThread = MDC.getCopyOfContextMap();
    if (parentContext == null) {
      MDC.clear();
    } else {
      MDC.setContextMap(parentContext);
    }
    return originalContextFromChildThread;
  }

  /**
   * Invoked after running a task.
   *
   * @param childContext the old MDC context
   */
  private static void resetChildContext(Map<String, String> childContext) {
    if (childContext == null) {
      MDC.clear();
    } else {
      MDC.setContextMap(childContext);
    }
  }
}
