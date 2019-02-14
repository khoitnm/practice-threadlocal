package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleCheckedExceptionUseCase;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleRuntimeExceptionUseCase;
import org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.service.SampleUseCase;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
public class SampleResource {

    private final SampleUseCase sampleUseCase;
    private final SampleRuntimeExceptionUseCase sampleRuntimeExceptionUseCase;
    private final SampleCheckedExceptionUseCase sampleCheckedExceptionUseCase;

    @Autowired
    public SampleResource(SampleUseCase sampleUseCase, SampleRuntimeExceptionUseCase sampleRuntimeExceptionUseCase, SampleCheckedExceptionUseCase sampleCheckedExceptionUseCase) {
        this.sampleUseCase = sampleUseCase;
        this.sampleRuntimeExceptionUseCase = sampleRuntimeExceptionUseCase;
        this.sampleCheckedExceptionUseCase = sampleCheckedExceptionUseCase;
    }

    @RequestMapping(value = {"/sample-api"})
    public String process() {
        return sampleUseCase.process();
    }

    @RequestMapping("/sample-api/runtime-exception/{status}")
    public String processRuntimeException(@PathVariable("status") String status) {
        return sampleRuntimeExceptionUseCase.process(status);
    }

    @RequestMapping("/sample-api/checked-exception/{status}")
    public String processCheckedException(@PathVariable("status") String status) {
        return sampleCheckedExceptionUseCase.process(status);
    }
}