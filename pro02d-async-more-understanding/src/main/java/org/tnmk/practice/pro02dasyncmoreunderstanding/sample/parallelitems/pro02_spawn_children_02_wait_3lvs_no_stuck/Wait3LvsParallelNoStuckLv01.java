package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro02_spawn_children_02_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsParallelNoStuckLv01 {
  private final Wait3LvsParallelNoStuckLv02 wait3LvsParallelNoStuckLv02;

  public String spawnChildren(int lv02Count, int lv03Count, int lv03Sleep) {

    String description = ProcessLogger.summary(this, null);
    ThreadLogger.log(description, Thread.currentThread());

    IntStream.range(0, lv02Count)
        .parallel()
        .forEach(lv02Index -> {
          log.info(description + ": Start adding Lv02Async[" + lv02Index + "]");
          wait3LvsParallelNoStuckLv02.spawnChildren(lv02Index, lv03Count, lv03Sleep);
        });

    log.info(description + " finished");
    return description + " finished";
  }
}
