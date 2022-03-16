package org.tnmk.practice.pro02asimplemdc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class ServiceB {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public void startB(){
    logger.info("startB");
  }
}
