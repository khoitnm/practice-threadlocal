package org.tnmk.practice.pro02dasyncmoreunderstanding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Pro02dAsyncMoreUnderstandingApplication {
    public static void main(String[] args) {
        SpringApplication.run(Pro02dAsyncMoreUnderstandingApplication.class, args);
    }

}
