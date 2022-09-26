package org.tnmk.practice.pro02dasyncmoreunderstanding.sample.parallelitems.pro02_spawn_children_02_wait_3lvs_no_stuck;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Wait3LvsParallelNoStuckController {
  private final Wait3LvsParallelNoStuckLv01 wait3LvsParallelNoStuckLv01;
  private static final String REQUEST_PATH = "/parallel/spawn-children/wait-3lvs-no-stuck";

  @GetMapping(REQUEST_PATH)
  public String asyncSpawnChildren(
      // the default lv02Count 10 will make thread pool stuck because threadPool CoreSize is only 8.
      //
      @RequestParam(value = "lv02Count", defaultValue = "11") int lv02Count,
      @RequestParam(value = "lv03Count", defaultValue = "2") int lv03Count,
      @RequestParam(value = "lv03Sleep", defaultValue = "1000") int lv03sleep) {
    log.info("API {} is running...", REQUEST_PATH);
    IntStream.range(0, 1)
        .parallel()
        .forEach(i ->
            wait3LvsParallelNoStuckLv01.spawnChildren(lv02Count, lv03Count, lv03sleep)
        );
    return "finished";
  }
}
