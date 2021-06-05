package org.tnmk.practice.pro03threadpool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.Future;

@Service
public class ServiceA {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  private final ServiceB serviceB;

  public ServiceA(ServiceB serviceB) {
    this.serviceB = serviceB;
  }

  // This @Async annotation requires a configuration in {@link AsyncConfig}
  @Async
  public Future<Integer> startA(int value) {
    MDC.put("samplekey", ""+value);
    logger.info("StartA {}",value);
    serviceB.startB();

    return new AsyncResult<>(value);
  }
}
