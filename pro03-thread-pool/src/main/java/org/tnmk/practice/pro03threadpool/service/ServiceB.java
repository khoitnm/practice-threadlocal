package org.tnmk.practice.pro03threadpool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class ServiceB {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public void startB(){
    String value = MDC.get("samplekey");

    logger.info("StartB {}",value);
  }
}
