package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro02_spawn_children_02_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ProcessLogger;
import org.tnmk.practice.pro02dasyncmoreunderstanding.common.ThreadLogger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class Wait3LvsParallelNoStuckLv03 {

  public String async(int lv02Index, int lv03Index, int sleep) {
    String description = ProcessLogger.summary(this, lv02Index, lv03Index);
    ThreadLogger.log(description, Thread.currentThread());
    try {
      List<Map<String, String>> objects = IntStream.range(0, sleep)
          .mapToObj(index -> createObject(index, 50))
          .collect(Collectors.toList());
      // create objects just to make this method consumes some memory.
      Thread.sleep(sleep);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info(description + " finished");
    return description + " finished";
  }

  private Map<String, String> createObject(int objectIndex, int fieldsCount) {
    Map<String, String> result = new HashMap<>();
    result.put("index", "" + objectIndex);
    for (int i = 0; i < fieldsCount; i++) {
      result.put("key" + i, "value" + i);
    }
    return result;
  }
}
