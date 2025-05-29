package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.springasync.pro00_simple_async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.common.ThreadLogger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleAsyncService {

    private final SimpleAsync simpleAsync;

    public String asyncSpawnChildren(
        int requestIndex,
        int sleep) throws ExecutionException, InterruptedException {
        String processTitle = String.format("%s[%s] before starting", this.getClass().getSimpleName(), requestIndex);
        ThreadLogger.log(processTitle, Thread.currentThread(), null);
        Future<String> future = simpleAsync.async(sleep);
        String result = future.get();
        return result;
    }
}
