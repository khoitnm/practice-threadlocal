package org.tnmk.practice.pro02copymdctochildthreads.sample.asynctasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SampleAsyncService {
    private static final Logger logger = LoggerFactory.getLogger(SampleAsyncService.class);

    @Async
    public void writeLog(){
        //MDC information will be also written into log messages.
        logger.info("Sample Async Service");
    }
}
