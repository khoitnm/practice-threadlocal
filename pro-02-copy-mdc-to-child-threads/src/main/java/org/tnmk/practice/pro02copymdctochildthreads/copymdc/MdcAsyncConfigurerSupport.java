package org.tnmk.practice.pro02copymdctochildthreads.copymdc;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync(proxyTargetClass = true)
@Configuration
public class MdcAsyncConfigurerSupport extends AsyncConfigurerSupport {
    private static final int TASK_CORE_POOL_SIZE = 5;
    private static final int TASK_MAX_POOL_SIZE = 10;
    private static final int TASK_QUEUE_SIZE = 5;

    /**
     * Customize async task executor thread pool, with MDC information copied for each task.
     *
     * @return - customized Task executor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(new MdcTaskDecorator());
        executor.setCorePoolSize(TASK_CORE_POOL_SIZE);
        executor.setMaxPoolSize(TASK_MAX_POOL_SIZE);
        executor.setQueueCapacity(TASK_QUEUE_SIZE);
        executor.initialize();
        return executor;
    }
}
