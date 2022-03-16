package org.tnmk.practice.pro02bcopymdctochildthreads.sample.asynctasks;

import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.time.Instant;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SampleAsyncTrigger {
  private final static Logger logger = getLogger(MethodHandles.lookup().lookupClass());

  @Autowired
  private SampleAsyncService sampleAsyncService;

  public void start(int asyncTasksCount) {
    MDC.put("triggeredDateTime", Instant.now().toString());
    logger.info("Start SampleAsyncTrigger");

    for (int i = 0; i < asyncTasksCount; i++) {
      sampleAsyncService.asyncWriteLog(i);
    }

    logger.info("End SampleAsyncTrigger");
  }
}
