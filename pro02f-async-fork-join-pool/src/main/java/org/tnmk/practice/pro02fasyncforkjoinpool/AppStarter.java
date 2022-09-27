package org.tnmk.practice.pro02fasyncforkjoinpool;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02fasyncforkjoinpool.common.ThreadLogger;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppStarter {

  @EventListener(ApplicationStartedEvent.class)
  public void start() {
    ThreadLogger.log("Start App", Thread.currentThread());
  }
}
