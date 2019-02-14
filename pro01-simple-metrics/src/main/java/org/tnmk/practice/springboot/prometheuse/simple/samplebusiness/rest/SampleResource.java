package org.tnmk.practice.springboot.prometheuse.simple.samplebusiness.rest;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tnmk.practice.springboot.prometheuse.simple.samplebusiness.service.SampleUseCase;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
//@Timed //enable timings on every request handler in the controller.
public class SampleResource {

    private final SampleUseCase sampleUseCase;

    @Autowired
    public SampleResource(SampleUseCase sampleUseCase) {
        this.sampleUseCase = sampleUseCase;
    }

    // Enable timings for an individual endpoint.
    // This is not necessary if you have it on the class,
    // but can be used to further customize the timer for this particular endpoint.
    @Timed("sample_resource")
    @RequestMapping(value = {"/sample-api"})
    public String sampleProcess(@RequestParam(value = "expectSuccess", required = false, defaultValue = ""+true) boolean expectSuccess) {
        return sampleUseCase.sampleProcess(expectSuccess);
    }
}