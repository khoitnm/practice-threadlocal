package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.testsupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
public class CleanUpSupport {
    public static void stopTaskExecutor(TaskExecutor taskExecutor) {
        if (taskExecutor instanceof ThreadPoolTaskExecutor executor) {
            log.info("Shutdown thread pool task executor!!!");
            // If we don't do that, the children threads still exist and running even after the test case finished.
            // We want to stop those children threads, so we need to forcefully stop the thread pool.
            executor.stop();
        }
    }
}
