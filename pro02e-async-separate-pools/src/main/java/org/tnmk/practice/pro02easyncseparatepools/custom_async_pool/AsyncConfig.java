package org.tnmk.practice.pro02easyncseparatepools.custom_async_pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro00_spawn_children_01_wait_3lvs_no_stuck.Wait3LvsNoStuckSupport;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Configuration
public class AsyncConfig {
//  @Bean("threadPoolTaskExecutor")
//  public TaskExecutor getAsyncExecutor(ThreadPoolTaskExecutor applicationTaskExecutor) {
//    return applicationTaskExecutor;
//  }

  @Bean(Wait3LvsNoStuckSupport.EXECUTOR_BEAN_NAME)
  public Executor customExecutor() {
    ForkJoinPool pool = new ForkJoinPool(
        Runtime.getRuntime().availableProcessors(), ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
    return pool;
  }
}
