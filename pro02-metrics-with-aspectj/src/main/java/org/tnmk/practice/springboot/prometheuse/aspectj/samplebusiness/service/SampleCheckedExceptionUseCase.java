package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.helper.DoSomethingHelper;

import java.util.concurrent.TimeUnit;

import static org.tnmk.practice.springboot.prometheuse.aspectj.metrics.MetricConstants.*;

@Service
public class SampleCheckedExceptionUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SampleCheckedExceptionUseCase.class);

    @Autowired
    private MeterRegistry meterRegistry;


    public String process(String status) {
        Timer timer = meterRegistry.timer(METRIC_NAME, TAG_NAME_STATUS, TAG_VALUE_STATUS_FAIL);
        long startTime = System.nanoTime();
        try {
            doSomethingWithCheckedException(status);
            timer = meterRegistry.timer(METRIC_NAME, TAG_NAME_STATUS, TAG_VALUE_STATUS_SUCCESS);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            //The Counter could be the same name as the Timer, but the Tags must be different.
        } finally {
            long endTime = System.nanoTime();
            timer.record(endTime - startTime, TimeUnit.NANOSECONDS);
            return String.format("timerId: %s, meanTime: %s, measure: %s, baseUnit: %s", timer.getId(), timer.mean(timer.baseTimeUnit()), timer.measure(), timer.baseTimeUnit());
        }
    }

    private void doSomethingWithCheckedException(String status) throws SampleCheckedException {
        if (status.equalsIgnoreCase("success")) {
            DoSomethingHelper.doInRandomSeconds(3);
        } else {
            throw new SampleCheckedException("I fail for you.");
        }
    }
}
