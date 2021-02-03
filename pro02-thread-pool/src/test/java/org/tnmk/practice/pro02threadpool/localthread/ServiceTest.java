package org.tnmk.practice.pro02threadpool.localthread;

import org.junit.jupiter.api.Test;

public class ServiceTest {

  ServiceA serviceA = new ServiceA();

  @Test
  public void test(){
    serviceA.startA();
  }
}
