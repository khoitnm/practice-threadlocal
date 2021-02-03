package org.tnmk.practice.pro02simplemdc.localthread;

import org.junit.jupiter.api.Test;

public class ServiceTest {

  ServiceA serviceA = new ServiceA();

  @Test
  public void test(){
    serviceA.startA();
    //No exception means good!
  }
}
