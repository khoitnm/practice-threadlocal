package org.tnmk.practice.pro03threadpool.service;

import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class ServiceA {
  private static int COUNT = 0;
  private final ServiceB serviceB;

  public ServiceA(ServiceB serviceB) {
    this.serviceB = serviceB;
  }

  // This @Async annotation requires a configuration in {@link AsyncConfig}
  @Async
  public Future<String> startA() {
    String value = "Val " + COUNT++;
    MDC.put("samplekey", value);
    serviceB.startB();
    return new AsyncResult<>(value);
  }
}
