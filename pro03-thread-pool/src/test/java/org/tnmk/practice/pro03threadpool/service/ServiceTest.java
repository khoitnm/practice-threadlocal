package org.tnmk.practice.pro03threadpool.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.practice.pro03threadpool.BaseIntegrationTest;

public class ServiceTest extends BaseIntegrationTest {

  @Autowired ServiceA serviceA;

  @Test
  public void test(){
    serviceA.startA();
  }
}
