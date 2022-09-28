package org.tnmk.practice.pro02fasyncforkjoinpool.common;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A {@link ForkJoinPool} that inherits MDC contexts from the thread that queues a task.
 *
 * @author Gili Tzabari
 * https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool
 */
public final class ContextualForkJoinPool extends ForkJoinPool {
  /**
   * Creates a new MdcForkJoinPool.
   *
   * @param parallelism the parallelism level. For default value, use {@link java.lang.Runtime#availableProcessors}.
   * @param factory     the factory for creating new threads. For default value, use
   *                    {@link #defaultForkJoinWorkerThreadFactory}.
   * @param handler     the handler for internal worker threads that terminate due to unrecoverable errors encountered
   *                    while executing tasks. For default value, use {@code null}.
   * @param asyncMode   if true, establishes local first-in-first-out scheduling mode for forked tasks that are never
   *                    joined. This mode may be more appropriate than default locally stack-based mode in applications
   *                    in which worker threads only process event-style asynchronous tasks. For default value, use
   *                    {@code false}.
   * @throws IllegalArgumentException if parallelism less than or equal to zero, or greater than implementation limit
   * @throws NullPointerException     if the factory is null
   * @throws SecurityException        if a security manager exists and the caller is not permitted to modify threads
   *                                  because it does not hold
   *                                  {@link java.lang.RuntimePermission}{@code ("modifyThread")}
   */
  public ContextualForkJoinPool(
      int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
    super(parallelism, factory, handler, asyncMode);
  }

  @Override
  public void execute(ForkJoinTask<?> task) {
    // See http://stackoverflow.com/a/19329668/14731
    super.execute(wrap(task));
  }

  @Override
  public void execute(Runnable task) {
    // See http://stackoverflow.com/a/19329668/14731
    super.execute(wrap(task));
  }

  @Override public ForkJoinTask<?> submit(Runnable task) {
    return super.submit(wrap(task));
  }

  @Override public <T> ForkJoinTask<T> submit(Callable<T> task) {
    return super.submit(wrap(task));
  }

  @Override public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task) {
    return super.submit(wrap(task));
  }

  @Override public <T> ForkJoinTask<T> submit(Runnable task, T result) {
    return super.submit(wrap(task), result);
  }

  private <T> ForkJoinTask<T> wrap(ForkJoinTask<T> task) {
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

  private Runnable wrap(Runnable task) {
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

  private Callable wrap(Callable task) {
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

  /**
   * Invoked before running a task.
   *
   * @param parentContext the new MDC context
   * @return the old MDC context
   */
  private Map<String, String> copyParentContext(Map<String, String> parentContext) {
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
  private void resetChildContext(Map<String, String> childContext) {
    if (childContext == null) {
      MDC.clear();
    } else {
      MDC.setContextMap(childContext);
    }
  }
}