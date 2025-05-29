package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.pro00_simple_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.common.ThreadLogger;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.custom_async_pool.AsyncSupport;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAsync {

    @Async(AsyncSupport.EXECUTOR_BEAN_NAME)
    public CompletableFuture<String> async(int sleep) {
        String processTitle = String.format("%s[%s]", this.getClass().getSimpleName(), null);
        ThreadLogger.log(processTitle, Thread.currentThread(), null);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(processTitle + " ended!");
        return CompletableFuture.completedFuture(processTitle);
    }
}
