package org.tnmk.practice.pro02dasyncmoreunderstanding.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadLogger {

    public static void log(ThreadPoolTaskExecutor executor) {
        log.info("ThreadPoolTaskExecutor: \n"
            + "\n\t isDaemon: " + executor.isDaemon()
            + "\n\t prefersShortLivedTasks: " + executor.prefersShortLivedTasks()
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
            + "\n\t getThreadPoolExecutor.getKeepAliveTime(): " + executor.getThreadPoolExecutor().getKeepAliveTime(TimeUnit.SECONDS) + " seconds"
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

    public static void log(String description, ForkJoinWorkerThread forkJoinWorkerThread) {
        ForkJoinPool forkJoinPool = forkJoinWorkerThread.getPool();
        log(description, forkJoinWorkerThread.getPoolIndex(), forkJoinPool);
    }

    public static void log(String description, Thread currentThread, ThreadPoolTaskExecutor executor) {
        if (currentThread instanceof ForkJoinWorkerThread forkJoinWorkerThread) {
            log(description, forkJoinWorkerThread);
        } else {
            String logMessage = description + ""
                + " ThreadGroup.name: " + currentThread.getThreadGroup().getName()
                + ", ThreadGroup.activeCount: " + currentThread.getThreadGroup().activeCount()
                + ", ThreadGroup.activeGroupCount: " + currentThread.getThreadGroup().activeGroupCount();
            if (executor != null) {
                logMessage += ", CorePoolSize: " + executor.getCorePoolSize()
                    + ", PoolSize: " + executor.getPoolSize()
                    + ", MaxPoolSize: " + executor.getMaxPoolSize()
                    + ", ActiveCount: " + executor.getActiveCount()
                    + ", ThreadPoolExecutor.getCompletedTaskCount: " + executor.getThreadPoolExecutor().getCompletedTaskCount()
                    + ", ThreadPoolExecutor.getTaskCount: " + executor.getThreadPoolExecutor().getTaskCount()
                    + ", ThreadPoolExecutor.getLargestPoolSize: " + executor.getThreadPoolExecutor().getLargestPoolSize()
                    + ", ThreadPoolExecutor.getMaximumPoolSize: " + executor.getThreadPoolExecutor().getMaximumPoolSize();
            }
            log.info(logMessage);
        }
    }

    public static void log(String description, Integer poolIndex, ForkJoinPool forkJoinPool) {
        log.info(description + ". ForkJoinPool [" + poolIndex + "]: \n"
            + "\tPoolSize: " + forkJoinPool.getPoolSize()
            + ", ActiveThreadCount: " + forkJoinPool.getActiveThreadCount()
            + ", RunningThreadCount: " + forkJoinPool.getRunningThreadCount()
            + ", QueuedTaskCount: " + forkJoinPool.getQueuedTaskCount()
            + ", QueuedSubmissionCount: " + forkJoinPool.getQueuedSubmissionCount()
            + ", Parallelism: " + forkJoinPool.getParallelism()
        );
    }
}
