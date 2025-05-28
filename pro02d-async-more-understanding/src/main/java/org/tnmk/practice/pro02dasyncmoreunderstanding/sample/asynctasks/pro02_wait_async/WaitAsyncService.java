package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_wait_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitAsyncService
{
    private final WaitAsyncLv1 lv1;

    public void asyncSpawnChildren(int lv01Count, int lv02Count) throws ExecutionException, InterruptedException {
        String description = "[Service]";// ProcessLogger.summary(this, null);
        ThreadLogger.log(description + " start... ", Thread.currentThread());

        CompletableFuture<?>[] futures = IntStream.range(0, lv01Count)
            .mapToObj(lv01Index -> {
                log.info(description + " add [" + lv01Index + "]");
                return lv1.runAsync(lv01Index, lv02Count);
            })
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        log.info(description + " finished");
    }
}
