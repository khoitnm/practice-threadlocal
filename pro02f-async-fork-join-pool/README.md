This project demonstrate how to use ForkJoinPool with Async.

We also try to cover more advanced aspect:

- Copy context (like MDC) from parent thread to child threads:
  https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool

- Exception handler
  https://www.javacodegeeks.com/2015/02/java8-multi-threading-forkjoinpool-dealing-with-exceptions.html

- Graceful shutdown

*UPDATED*
In Java 21, we can use VirtualThread instead of ForkJoinPool, it's much simpler, please view [AsyncConfig](./src/main/java/org/tnmk/practice/pro02easyncforkjoinpoolsimple/custom_async_pool/AsyncConfig.java)

# Q&A

## Question 1:

In JorkJoinTask: "Subdividable tasks should also not perform blocking I/O", why's that?

- Is it because "These guidelines are loosely enforced by not permitting checked exceptions such as {@code IOExceptions} to be thrown."?
- Answer: https://stackoverflow.com/questions/28753498/java-fork-join-executor-usage-for-db-access
  "Underlying the fork join pool is shared amount of threads, if there's some IO work blocking on those threads, then less threads for CPU intensive
  work. other none blocking work will starve."
- Another answer: https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html
  "The pool attempts to maintain enough active (or available) threads by dynamically adding, suspending, or resuming internal worker threads, even if
  some tasks are stalled waiting to join others.
  However, no such adjustments are guaranteed in the face of blocked I/O or other unmanaged synchronization."

## Question 2:

In ContextualForkJoinPool, why we only override `execute(...)` methods, but not `submit(...)` methods?
Links that related to the question:

- https://stackoverflow.com/questions/52840346/mdc-context-getting-copied-to-only-one-thread-of-forkjoinpool
- https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool

First, we need to understand the difference between execute(...) vs. submit(...)
https://stackoverflow.com/questions/17881183/difference-between-execute-submit-and-invoke-in-a-forkjoinpool
https://javarevisited.blogspot.com/2016/04/difference-between-ExecutorServie-submit-vs-Executor-execute-method-in-Java.html

- Both `excute()` and `submit()` will try to put the task to the queue, and waiting for the pool to put it into an available thread.
- The difference is: `execute()` only returns `void`, while `submit()` return `Future<>` (`ForkJoinTask`, to be specific).

## Question 3:

In `ContextualForkJoinPool.wrap(java.util.concurrent.ForkJoinTask<T>)`, why do we have to override `getRawResult()` and `setRawResult()`.
Answer:
Because we cannot override `setRawResult()` like this:

``` 
      @Override
      protected void setRawResult(T value) {
        task.setRawResult(value); // this method is protected, so we cannot call that method from the parent task.
      }
```