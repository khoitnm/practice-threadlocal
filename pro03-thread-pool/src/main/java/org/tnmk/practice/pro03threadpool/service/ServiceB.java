package org.tnmk.practice.pro03threadpool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;

@Service
public class ServiceB {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public void startB(){
    logger.info("startB");
  }
}
