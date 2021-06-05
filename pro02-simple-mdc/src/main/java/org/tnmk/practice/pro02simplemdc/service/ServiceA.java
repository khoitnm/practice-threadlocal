package org.tnmk.practice.pro02simplemdc.service;

import org.slf4j.MDC;

public class ServiceA {
  private final ServiceB serviceB = new ServiceB();

  public void startA(){
    MDC.put("samplekey","samplevalue"+System.nanoTime());
    serviceB.startB();
  }
}
