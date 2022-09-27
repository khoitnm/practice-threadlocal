package org.tnmk.practice.pro02fasyncforkjoinpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Pro02fAsyncForkJoinPoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(Pro02fAsyncForkJoinPoolApplication.class, args);
    }

}
