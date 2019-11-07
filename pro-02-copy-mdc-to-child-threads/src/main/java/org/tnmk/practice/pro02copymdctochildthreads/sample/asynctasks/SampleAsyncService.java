package org.tnmk.practice.pro02copymdctochildthreads.sample.asynctasks;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SampleAsyncService {
    private static final Logger logger = LoggerFactory.getLogger(SampleAsyncService.class);

    @Async
    public void writeLog(){
        //This will be run in [lTaskExecutor-3] and able to get MDC value.
        MDC.put("asyncNanoTime", ""+System.nanoTime());
        logger.info("Sample Async Service"+ MDC.getCopyOfContextMap());
    }
}
