package org.tnmk.practice.pro02fasyncforkjoinpool.common.decorated_forkjoinpool;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinTask;

public interface TaskDecorator {
  Runnable decorate(Runnable task);

  Callable decorate(Callable task);

  <T> ForkJoinTask<T> decorate(ForkJoinTask<T> task);
}
