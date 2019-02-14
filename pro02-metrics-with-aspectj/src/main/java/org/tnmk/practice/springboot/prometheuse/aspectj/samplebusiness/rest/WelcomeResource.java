package org.tnmk.practice.springboot.prometheuse.aspectj.samplebusiness.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This REST API will be exposed via the port 8080
 * While metrics APIs will be exposed via the port 9090
 */
@RestController
public class WelcomeResource {

    @RequestMapping(value = {"/", "/welcome"})
    public String welcome() {
        return "Greetings from Spring Boot!";
    }
}