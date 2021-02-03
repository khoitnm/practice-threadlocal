package org.tnmk.practice.pro03threadpool.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practice.pro03threadpool.BaseIntegrationTest;

import java.lang.invoke.MethodHandles;

public class ServiceTest extends BaseIntegrationTest {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired ServiceA serviceA;

  @Test
  public void test() {
    int threadsCount = 5;
    for (int i = 0; i < threadsCount; i++) {
      serviceA.startA();
    }
    logger.info("The end of test case. This message should be print BEFORE messages in ServiceA because ServiceA is run asynchronously.");
  }
}
