package org.tnmk.practice.pro02gasyncvirtualthreadsimple.custom_async_pool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tnmk.practice.pro02gasyncvirtualthreadsimple.sample.springasync.pro02_wait_async.WaitAsyncService;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@Configuration
public class AsyncConfig {
    /**
     * This pool will help {@link WaitAsyncService} won't get stuck anymore regardless how many child threads in level 1 & 2!<br/>
     * If you want your async logic run with this Executor, just you {@code Async(AsyncSupport.EXECUTOR_BEAN_NAME)}.
     */
    @Bean(AsyncSupport.EXECUTOR_BEAN_NAME)
    public Executor customExecutor() {
        return Executors.newVirtualThreadPerTaskExecutor();
    }
}
