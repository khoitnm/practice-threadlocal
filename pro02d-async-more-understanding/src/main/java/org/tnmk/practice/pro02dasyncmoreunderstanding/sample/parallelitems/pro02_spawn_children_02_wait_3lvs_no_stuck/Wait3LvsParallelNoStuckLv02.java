package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro02_spawn_children_02_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsParallelNoStuckLv02 {
  private final Wait3LvsParallelNoStuckLv03 wait3LvsParallelNoStuckLv03;

  public String spawnChildren(final int lv02Index, int childrenCount, int lv03Sleep) {
    String description = ProcessLogger.summary(this, lv02Index);
    ThreadLogger.log(description, Thread.currentThread());

    IntStream.range(0, childrenCount)
        .parallel()
        .forEach(lv03Index -> {
          log.info(description + ": Start adding Lv03Async[" + lv03Index + "]");
          wait3LvsParallelNoStuckLv03.async(lv02Index, lv03Index, lv03Sleep);
        });

    log.info(description + " finished");
    return description + " finished";

  }
}
