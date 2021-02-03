package org.tnmk.practice.pro03threadpool.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practice.pro03threadpool.BaseIntegrationTest;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ServiceTest extends BaseIntegrationTest {
  private final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Autowired ServiceA serviceA;

  @Test
  public void test() {
    int threadsCount = 30;
    for (int i = 0; i < threadsCount; i++) {
      Future<Integer> result = serviceA.startA(i);
//      result.
    }
    logger.info("The end of test case. This message should be print BEFORE messages in ServiceA because ServiceA is run asynchronously.");
  }
}
