package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.asynctasks.pro02_spawn_children_03_wait_2lvs_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait2LvsStuck_Lv01Async {
    private final Wait2LvsStuck_Lv02Async lv2;
    //private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Async
    public CompletableFuture<String> runAsync(int lv01Index, int lv02Count) {

        String description = "[%s]".formatted(lv01Index);// ProcessLogger.summary(this, lv01Index);
        ThreadLogger.log(description + " start...", Thread.currentThread());

        CompletableFuture<?>[] futures = IntStream.range(0, lv02Count)
            .mapToObj(lv02Index -> {
                log.info(description + " add [" + lv01Index + "][" + lv02Index + "]");
                return lv2.runAsync(lv01Index, lv02Index);
            })
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(futures).join();
        log.info(description + " finished");
        return CompletableFuture.completedFuture("Lv01 finished");
    }
}
