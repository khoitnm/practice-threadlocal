package org.tnmk.practice.pro02dasyncseparatepools;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncseparatepools.common.ThreadLogger;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppStarter {
  private final ThreadPoolTaskExecutor applicationTaskExecutor;

  @EventListener(ApplicationStartedEvent.class)
  public void start() {
    ThreadLogger.log(applicationTaskExecutor);
  }
}
