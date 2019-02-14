package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleRuntimeExceptionUseCase;

import java.util.Random;

public class DoSomethingHelper {
    private static final Logger logger = LoggerFactory.getLogger(DoSomethingHelper.class);

    public static void doInRandomSeconds(int seconds) {
        try {
            long runTime = new Random().nextInt(seconds * 1000);
            Thread.sleep(runTime);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }
}
