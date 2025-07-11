As we see in `pro02d-async-more-understanding`, `@Async` use `ThreadPoolTaskExecutor`;
and it will get stuck in this test case `org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.pro02_wait_async.WaitAsyncServiceTest.test_when_2Levels_If_Lv1AndLv2_GreaterThan_CoreSize_Stuck`

However, when using Virtual Thread for `@Async`, it won't get stuck: `org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro01_wait_parallelstream.WaitParallelStreamServiceTest.test_when_2Levels_never_get_Stuck`
- Please view how VirtualThread is configured as Executor in [AsyncConfig](./src/main/java/org/tnmk/practice/pro02gasyncvirtualthreadsimple/custom_async_pool/AsyncConfig.java)
- In your business logic, use `@Async("customExecutorBean")` so that `@Async` will use that customized executor.

Note that this example hasn't handled the following cases:
- Copy context (like MDC) from parent thread to child threads:
  https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool
- Exception handler
  https://www.javacodegeeks.com/2015/02/java8-multi-threading-forkjoinpool-dealing-with-exceptions.html
- Graceful shutdown
