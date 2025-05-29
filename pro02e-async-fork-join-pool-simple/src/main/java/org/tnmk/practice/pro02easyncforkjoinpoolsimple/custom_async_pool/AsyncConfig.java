package org.tnmk.practice.pro02easyncforkjoinpoolsimple.custom_async_pool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.pro02_wait_async.WaitAsyncService;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

@Configuration
public class AsyncConfig {
//    @Value("${spring.task.execution.pool.core-size:}")
//    private Integer corePoolSize;

    /**
     * The default application TaskExecutor that's used by {@link Async} annotation was "applicationTaskExecutor".
     * And bellow commented out code show how such bean was created in Spring Boot.
     * However, we don't need this bean in this example, so I just commented it out.
     */
//    @Bean("applicationTaskExecutor")
//    public TaskExecutor applicationTaskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        if (corePoolSize != null) {
//            executor.setCorePoolSize(corePoolSize);
//        }
//        executor.setThreadNamePrefix("DefaultAsync");
//        executor.initialize();
//        return executor;
//    }


    /**
     * Please view explanation in `application.yml` file.
     */


    /**
     * This pool will help {@link WaitAsyncService} won't get stuck anymore regardless how many child threads in level 1 & 2!<br/>
     * If you want your async logic run with this Executor, just you {@code Async(AsyncSupport.EXECUTOR_BEAN_NAME)}.
     */
    @Bean(AsyncSupport.EXECUTOR_BEAN_NAME)
    public Executor customExecutor(
        // Please view explanation in `application.yml` file.
        @Value("${async.pool.parallelism:}") Integer asyncPoolParallelism
    ) {
        int parallelism = asyncPoolParallelism != null ? asyncPoolParallelism : ForkJoinPool.commonPool().getParallelism();
        ForkJoinPool pool = new ForkJoinPool(
            parallelism,
            ForkJoinPool.defaultForkJoinWorkerThreadFactory,
            null,
            true);
        return pool;
    }
}
