package org.tnmk.practice.pro02easyncforkjoinpoolsimple.sample.parallelitems.pro01_wait_parallelstream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02easyncforkjoinpoolsimple.common.ThreadLogger;

@Slf4j
@Service
@RequiredArgsConstructor
public class WaitParallelStreamLv2 {

    public void run(int lv1Index, int lv2Index) {
        String description = "[%s][%s]".formatted(lv1Index, lv2Index);
        ThreadLogger.log(description + " start... ", Thread.currentThread(), null);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info(description + " finished");
    }
}
