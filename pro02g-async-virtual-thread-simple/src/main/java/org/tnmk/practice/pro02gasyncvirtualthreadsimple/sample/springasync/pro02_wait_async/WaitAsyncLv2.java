package org.tnmk.practice.pro02gasyncvirtualthreadsimple.sample.springasync.pro02_wait_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02gasyncvirtualthreadsimple.common.ThreadLogger;
import org.tnmk.practice.pro02gasyncvirtualthreadsimple.custom_async_pool.AsyncSupport;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitAsyncLv2 {

    @Async(AsyncSupport.EXECUTOR_BEAN_NAME)
    public CompletableFuture<String> runAsync(int lv1Index, int lv2Index) {
        String description = "[%s][%s]".formatted(lv1Index, lv2Index);
        ThreadLogger.log(description + " start... ", Thread.currentThread(), null);
//        ThreadLogger.log(threadPoolTaskExecutor);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(description + " finished");
        return CompletableFuture.completedFuture("");
    }
}
