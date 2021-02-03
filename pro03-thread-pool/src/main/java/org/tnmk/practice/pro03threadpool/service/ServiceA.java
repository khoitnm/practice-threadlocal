package org.tnmk.practice.pro03threadpool.service;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class ServiceA {
  private final ServiceB serviceB;

  public ServiceA(ServiceB serviceB) {
    this.serviceB = serviceB;
  }

  public void startA(){
    MDC.put("samplekey","samplevalue"+System.nanoTime());
    serviceB.startB();
  }
}
