package org.tnmk.practice.pro02dasyncseparatepools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.SampleAsyncService;
import org.tnmk.practice.pro02dasyncseparatepools.sample.asynctasks.SampleAsyncTrigger;
import org.tnmk.practice.pro02dasyncseparatepools.sample.parallelitems.ParallelItemsProcessing;

@Service
public class Initiation {

  @Autowired
  private SampleAsyncTrigger sampleAsyncTrigger;
  @Autowired
  private ParallelItemsProcessing parallelItemsProcessing;

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    sampleAsyncTrigger.start(SampleAsyncService.MAX_ITEM);
    parallelItemsProcessing.processItemsConcurrently(ParallelItemsProcessing.MAX_ITEM);
  }
}
