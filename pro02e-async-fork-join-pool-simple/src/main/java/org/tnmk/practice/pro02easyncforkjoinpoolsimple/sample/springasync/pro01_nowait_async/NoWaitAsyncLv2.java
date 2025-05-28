package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.pro01_nowait_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoWaitAsyncLv2
{
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Async
    public CompletableFuture<String> runAsync(int lv1Index, int lv2Index) {
        String description = "[%s][%s]".formatted(lv1Index, lv2Index);
        ThreadLogger.log(description + " start... ", Thread.currentThread(), threadPoolTaskExecutor);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(description + " finished");
        return CompletableFuture.completedFuture("");
    }
}
