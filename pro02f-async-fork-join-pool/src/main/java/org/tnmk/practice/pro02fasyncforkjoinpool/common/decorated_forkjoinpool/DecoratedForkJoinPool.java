package org.tnmk.practice.pro02fasyncforkjoinpool.common.decorated_forkjoinpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * A {@link ForkJoinPool} that inherits MDC contexts from the thread that queues a task.
 *
 * @author Gili Tzabari
 * https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool
 */
public class DecoratedForkJoinPool extends ForkJoinPool {
  private final ForkJoinTaskDecorator forkJoinTaskDecorator;

  /**
   * Creates a new MdcForkJoinPool.
   *
   * @param parallelism the parallelism level. For default value, use {@link Runtime#availableProcessors}.
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
   *                                  {@link RuntimePermission}{@code ("modifyThread")}
   */
  public DecoratedForkJoinPool(
      MdcForkJoinTaskDecorator taskDecorator,
      int parallelism, ForkJoinWorkerThreadFactory factory, Thread.UncaughtExceptionHandler handler, boolean asyncMode) {
    super(parallelism, factory, handler, asyncMode);
    this.forkJoinTaskDecorator = taskDecorator;
  }

  @Override
  public void execute(ForkJoinTask<?> task) {
    // See http://stackoverflow.com/a/19329668/14731
    super.execute(forkJoinTaskDecorator.decorate(task));
  }

  @Override
  public void execute(Runnable task) {
    // See http://stackoverflow.com/a/19329668/14731
    super.execute(forkJoinTaskDecorator.decorate(task));
  }

  @Override public ForkJoinTask<?> submit(Runnable task) {
    return super.submit(forkJoinTaskDecorator.decorate(task));
  }

  @Override public <T> ForkJoinTask<T> submit(Callable<T> task) {
    return super.submit(forkJoinTaskDecorator.decorate(task));
  }

  @Override public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task) {
    return super.submit(forkJoinTaskDecorator.decorate(task));
  }

  @Override public <T> ForkJoinTask<T> submit(Runnable task, T result) {
    return super.submit(forkJoinTaskDecorator.decorate(task), result);
  }

}