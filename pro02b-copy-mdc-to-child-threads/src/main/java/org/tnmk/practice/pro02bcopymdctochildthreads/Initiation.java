package org.tnmk.practice.pro02bcopymdctochildthreads;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.tnmk.practice.pro02bcopymdctochildthreads.sample.asynctasks.SampleAsyncTrigger;
import org.tnmk.practice.pro02bcopymdctochildthreads.sample.parallelitems.ParallelItemsProcessing;

@Service
public class Initiation {

    @Autowired
    private SampleAsyncTrigger sampleAsyncTrigger;
    @Autowired
    private ParallelItemsProcessing parallelItemsProcessing;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        sampleAsyncTrigger.start(10);
        parallelItemsProcessing.processItemsConcurrently(10);
    }
}
