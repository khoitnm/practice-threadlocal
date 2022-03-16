package org.tnmk.practice.pro02bcopymdctochildthreads.copymdc;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

/**
 * This class will copy the MDC data (including correlation ID) into async task threads.
 * This ensures exceptions in the async task can be traced back to the originating gRPC call.
 */
@SuppressWarnings("PMD")
public class MdcTaskDecorator implements TaskDecorator {
    /**
     * Copy MDC data from the gRPC thread to async task thread.
     *
     * @param runnable - the Task runnable we are decorating
     * @return - decorated Runnable with MDC context copied
     */
    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> originalMdcContext = MDC.getCopyOfContextMap();
        return () -> {
            try {
                MDC.setContextMap(originalMdcContext);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
