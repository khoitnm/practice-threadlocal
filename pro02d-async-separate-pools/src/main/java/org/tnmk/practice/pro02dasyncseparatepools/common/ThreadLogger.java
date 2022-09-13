package org.tnmk.practice.pro02dasyncseparatepools.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
public class ThreadLogger {
  public static void log(ThreadPoolTaskExecutor executor) {
    log.info("ThreadPoolTaskExecutor: \n"
        + "\n\t getCorePoolSize: " + executor.getCorePoolSize()
        + "\n\t getPoolSize: " + executor.getPoolSize()
        + "\n\t getMaxPoolSize: " + executor.getMaxPoolSize()
        + "\n\t getActiveCount: " + executor.getActiveCount()
        + "\n\t getThreadNamePrefix: " + executor.getThreadNamePrefix()
        + "\n\t getKeepAliveSeconds: " + executor.getKeepAliveSeconds()
        + "\n"
        + "\n\t getThreadPoolExecutor.allowsCoreThreadTimeOut: " + executor.getThreadPoolExecutor().allowsCoreThreadTimeOut()
        + "\n\t getThreadPoolExecutor.getCompletedTaskCount: " + executor.getThreadPoolExecutor().getCompletedTaskCount()
        + "\n\t getThreadPoolExecutor.getTaskCount: " + executor.getThreadPoolExecutor().getTaskCount()
        + "\n\t getThreadPoolExecutor.getLargestPoolSize: " + executor.getThreadPoolExecutor().getLargestPoolSize()
        + "\n\t getThreadPoolExecutor.getMaximumPoolSize: " + executor.getThreadPoolExecutor().getMaximumPoolSize()
        + "\n"
        + "\n\t getThreadPoolExecutor.getQueue.remainingCapacity: " + executor.getThreadPoolExecutor().getQueue().remainingCapacity()
    );

  }

  public static void logSummary(String description, ThreadPoolTaskExecutor executor) {
    log.info(description + ". Summary Pool: \n"
        + "\tactive: " + executor.getActiveCount()
        + ", completedTaskCount: " + executor.getThreadPoolExecutor().getCompletedTaskCount()
        + ", taskCount: " + executor.getThreadPoolExecutor().getTaskCount()
        + ", poolSize: " + executor.getThreadPoolExecutor().getPoolSize()
        + ", queue.remainingCapacity: " + executor.getThreadPoolExecutor().getQueue().remainingCapacity()
        + ", queue.size: " + executor.getThreadPoolExecutor().getQueue().size()
    );

  }
}
