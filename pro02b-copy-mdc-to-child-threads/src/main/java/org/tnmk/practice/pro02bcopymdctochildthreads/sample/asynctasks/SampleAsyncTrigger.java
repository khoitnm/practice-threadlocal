package org.tnmk.practice.pro02bcopymdctochildthreads.sample.asynctasks;

import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleAsyncTrigger {

    private static final Logger logger = LoggerFactory.getLogger(SampleAsyncService.class);

    @Autowired
    private SampleAsyncService sampleAsyncService;

    public void start(int asyncTasksCount) {
        MDC.put("triggeredDateTime", Instant.now().toString());
        logger.info("Start SampleAsyncTrigger");

        for (int i = 0; i < asyncTasksCount; i++) {
            sampleAsyncService.writeLog();
        }
    }
}
