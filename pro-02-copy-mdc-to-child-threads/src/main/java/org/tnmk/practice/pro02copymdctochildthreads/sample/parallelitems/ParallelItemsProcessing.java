package org.tnmk.practice.pro02copymdctochildthreads.sample.parallelitems;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02copymdctochildthreads.sample.asynctasks.SampleAsyncService;

@Service
public class ParallelItemsProcessing {
    private static final Logger logger = LoggerFactory.getLogger(SampleAsyncService.class);

    public void processItemsCocurrently(int itemsCount) {
        MDC.put("triggeredDateTime", Instant.now().toString());
        logger.info("Start creating items");
        List<String> items = generateList(itemsCount);
        items.parallelStream().forEach((item) -> {
            MDC.put("asyncNanoTime", ""+System.nanoTime());
            //FIXME When the thread is [onPool-worker-1], it cannot get value from the original MDC.
            // It only shows MDC values when running in the [main] threads.
            logger.info("Processing item " + item + MDC.getCopyOfContextMap());
        });
    }

    private List<String> generateList(int itemsCount) {
        List<String> list = new ArrayList<>(itemsCount);
        for (int i = 0; i < itemsCount; i++) {
            list.add("Item_" + i);
        }
        return list;
    }
}
