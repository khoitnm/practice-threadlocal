package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.helper.DoSomethingHelper;

import static org.tnmk.practice.springboot.prometheuse.aspectj.metrics.MetricConstants.METRIC_NAME;
import static org.tnmk.practice.springboot.prometheuse.aspectj.metrics.MetricConstants.TAG_NAME_STATUS;
import static org.tnmk.practice.springboot.prometheuse.aspectj.metrics.MetricConstants.TAG_VALUE_STATUS_SUCCESS;

@Service
public class SampleUseCase {
    private static final Logger logger = LoggerFactory.getLogger(SampleUseCase.class);

    @Autowired
    private MeterRegistry meterRegistry;

    //This @Timed annotation will also count the number of times this method is triggered.
    @Timed(value = METRIC_NAME, extraTags = {TAG_NAME_STATUS, TAG_VALUE_STATUS_SUCCESS}
            ,longTask = true
            ,histogram = true

            // Read this to understand more about percentiles: https://www.elastic.co/blog/averages-can-dangerous-use-percentile
            // Important: percentiles is very expensive for calculation, please only use it when necessary.
            ,percentiles = {0.1, 0.5, 0.95, 0.99}
    )
    public String process() {
        DoSomethingHelper.doInRandomSeconds(3);

        logger.info("SampleUseCase.process");

        Meter meter = meterRegistry.find(METRIC_NAME).meter();
        if (meter != null) {
            return meter.measure().toString();
        } else {
            return "No meter " + METRIC_NAME + " yet, you need to call this method one more.";
        }
    }

}
