package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.pro02_wait_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.common.ThreadLogger;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.custom_async_pool.AsyncSupport;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitAsyncLv1 {
    private final WaitAsyncLv2 lv2;

    @Async(AsyncSupport.EXECUTOR_BEAN_NAME)
    public CompletableFuture<String> runAsync(int lv01Index, int lv02Count) {

        String description = "[%s]".formatted(lv01Index);
        ThreadLogger.log(description + " start...", Thread.currentThread(), null);

        if (lv02Count > 0) {
            CompletableFuture<?>[] futures = IntStream.range(0, lv02Count)
                .mapToObj(lv02Index -> {
                    log.info(description + " add [" + lv01Index + "][" + lv02Index + "]");
                    return lv2.runAsync(lv01Index, lv02Index);
                })
                .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(futures).join();
        }
        log.info(description + " finished");
        return CompletableFuture.completedFuture("Lv01 finished");
    }
}
