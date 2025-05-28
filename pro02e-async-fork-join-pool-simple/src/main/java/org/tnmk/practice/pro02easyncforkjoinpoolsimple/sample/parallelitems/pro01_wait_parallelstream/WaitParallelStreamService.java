package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.parallelitems.pro01_wait_parallelstream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.common.ThreadLogger;

import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitParallelStreamService {
    private final WaitParallelStreamLv1 lv1;

    public void asyncSpawnChildren(int lv01Count, int lv02Count) {
        String description = "[Service]";
        ThreadLogger.log(description + " start... ", Thread.currentThread(), null);

        IntStream.range(0, lv01Count)
            .parallel()
            .forEach(lv01Index -> {
                log.info(description + " add [" + lv01Index + "]");
                lv1.run(lv01Index, lv02Count);
            });

        log.info(description + " finished");
    }
}
