This module demonstrates that by using ForkJoinPool, @Async process doesn't get stuck anymore.
https://stackoverflow.com/questions/36026402/how-to-use-mdc-with-forkjoinpool
Note: using `@Async` combine with `ForkJoinPool` are configured in:
- `AsyncConfig`
- Using `@Async(AsyncSupport.EXECUTOR_BEAN_NAME)` in Service classes.

Note: in this module, the configuration of `AsyncConfig` is very simple, and hence doesn't take care of following aspect:
- Copy Context (such as MDC) from parent thread to child threads automatically
- Exception Handler
