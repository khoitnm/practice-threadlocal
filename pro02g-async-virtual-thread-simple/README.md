As we see in `pro02d-async-more-understanding`, `@Async` use `ThreadPoolTaskExecutor`;
and it will get stuck in this test case `org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.pro02_wait_async.WaitAsyncServiceTest.test_when_2Levels_If_Lv1AndLv2_GreaterThan_CoreSize_Stuck`

However, another async mechanism, `ForkJoinPool`, won't get stuck: `org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro01_wait_parallelstream.WaitParallelStreamServiceTest.test_when_2Levels_never_get_Stuck`

So, this example configure `@Async` to use a `ForkJoinPool` instead of `ThreadPoolTaskExecutor` in a simplest way:
- `ForkJoinPool` as Executor in `AsyncConfig`.
- In your business logic, use `@Async("customExecutorBean")` so that `@Async` will use the `ForkJoinPool` as the executor.
- Parallelism is configured by property `async.pool.parallelism` in `application.yml`.

Note that this example hasn't handled the following cases:
- Copy context (like MDC) from parent thread to child threads:
  https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool
- Exception handler
  https://www.javacodegeeks.com/2015/02/java8-multi-threading-forkjoinpool-dealing-with-exceptions.html
- Graceful shutdown

*UPDATED*
In Java 21, we can use VirtualThread instead of ForkJoinPool, it's much simpler, please view [AsyncConfig](./src/main/java/org/tnmk/practice/pro02gasyncvirtualthreadsimple/custom_async_pool/AsyncConfig.java)