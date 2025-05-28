package org.tnmk.practice.pro02easyncseparatepools.custom_async_pool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro01_spawn_children_01_wait_3lvs_no_stuck.Wait3LvsNoStuckController;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Configuration
public class AsyncConfig {
  /**
   * I try to make this bean similar to the default applicationTaskExecutor in Spring framework as much as possible.
   * And in {@link org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro01_spawn_children_00_wait_3lvs_stuck},
   * it also get stuck when trigger (which is expected with this kind of ThreadPool).
   */
  @Bean("applicationTaskExecutor")
  public TaskExecutor applicationTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setThreadNamePrefix("DefaultAsync");
    executor.initialize();
    return executor;
  }

  /**
   * This pool shows that when {@link org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro01_spawn_children_01_wait_3lvs_no_stuck}
   * runs with exactly the same logic as {@link org.tnmk.practice.pro02easyncseparatepools.custom_async_pool.pro01_spawn_children_00_wait_3lvs_stuck},
   * the {@link Wait3LvsNoStuckController} won't get stuck!
   * @return
   */
  @Bean(AsyncSupport.EXECUTOR_BEAN_NAME)
  public Executor customExecutor() {
    return regularForkJoinPool();
  }

  private ForkJoinPool regularForkJoinPool(){
    ForkJoinPool pool = new ForkJoinPool(
        Runtime.getRuntime().availableProcessors(),
        ForkJoinPool.defaultForkJoinWorkerThreadFactory,
        null,
        true);
    return pool;
  }
}
