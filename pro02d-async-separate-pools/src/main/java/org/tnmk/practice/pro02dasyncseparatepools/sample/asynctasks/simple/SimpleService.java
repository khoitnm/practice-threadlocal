package org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleService {
  @Async
  @SneakyThrows
  public void async() {
    log.info("simple async start...");
    Thread.sleep(1000);
    log.info("simple async finished");
  }
}
