package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.springasync.pro01_nowait_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoWaitAsyncLv1 {
    private final NoWaitAsyncLv2 lv2;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Async
    public CompletableFuture<String> runAsync(int lv01Index, int lv02Count) {

        String description = "[%s]".formatted(lv01Index);
        ThreadLogger.log(description + " start...", Thread.currentThread(), threadPoolTaskExecutor);

        if (lv02Count > 0) {
            CompletableFuture<?>[] futures = IntStream.range(0, lv02Count)
                .mapToObj(lv02Index -> {
                    log.info(description + " add [" + lv01Index + "][" + lv02Index + "]");
                    return lv2.runAsync(lv01Index, lv02Index);
                })
                .toArray(CompletableFuture[]::new);

            // Don't wait: CompletableFuture.allOf(futures).join();
        }
        log.info(description + " finished");
        return CompletableFuture.completedFuture("Lv01 finished");
    }
}
