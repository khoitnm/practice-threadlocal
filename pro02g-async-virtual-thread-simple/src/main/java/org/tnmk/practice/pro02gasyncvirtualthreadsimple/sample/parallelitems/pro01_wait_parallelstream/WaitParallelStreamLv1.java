package org.tnmk.practice.pro02gasyncvirtualthreadsimple.sample.parallelitems.pro01_wait_parallelstream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02gasyncvirtualthreadsimple.common.ThreadLogger;

import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitParallelStreamLv1 {
    private final WaitParallelStreamLv2 lv2;

    public void run(int lv01Index, int lv02Count) {

        String description = "[%s]".formatted(lv01Index);
        ThreadLogger.log(description + " start...", Thread.currentThread(), null);

        if (lv02Count > 0) {
            IntStream.range(0, lv02Count)
                .parallel() // run async
                .forEach(lv02Index -> {
                    log.info(description + " add [" + lv01Index + "][" + lv02Index + "]");
                    lv2.run(lv01Index, lv02Index);
                });
        }
        log.info(description + " finished");
    }
}
