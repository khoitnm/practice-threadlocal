package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_2lvs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait2Lvs_Lv02Async
{
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Async
    public CompletableFuture<String> runAsync(int lv1Index, int lv2Index) {
        String description = "[%s][%s]".formatted(lv1Index, lv2Index);//ProcessLogger.summary(this, lv1Index, lv2Index);
        ThreadLogger.log(description + " start... ", Thread.currentThread());
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
