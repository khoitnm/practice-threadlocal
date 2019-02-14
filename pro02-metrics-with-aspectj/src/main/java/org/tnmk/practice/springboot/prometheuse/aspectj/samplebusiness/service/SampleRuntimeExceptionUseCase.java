package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.helper.DoSomethingHelper;

import static org.tnmk.practice.springboot.prometheuse.aspectj.metrics.MetricConstants.*;

@Service
public class SampleRuntimeExceptionUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SampleRuntimeExceptionUseCase.class);

    @Autowired
    private MeterRegistry meterRegistry;


    public String process(String status) {
        try {
            //Timer must have a different name from Counter.
            //Note: timer includes count, so you don't actually need count anymore.
            Timer timer = meterRegistry.timer(METRIC_NAME, TAG_NAME_STATUS, TAG_VALUE_STATUS_SUCCESS);
            timer.record(() -> doSomethingWithRuntimeException(status));
            return "Success Measure: " + timer.measure();
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);

            //The Counter could be the same name as the Timer, but the Tags must be different.
            Counter counter = meterRegistry.counter(METRIC_NAME, TAG_NAME_STATUS, TAG_VALUE_STATUS_FAIL);
            counter.increment();
            return "Fail Measure: " + counter.measure();
        }
    }

    private void doSomethingWithRuntimeException(String status) {
        if (status.equalsIgnoreCase("success")) {
            DoSomethingHelper.doInRandomSeconds(3);
        } else {
            throw new RuntimeException("I fail for you.");
        }
    }
}
