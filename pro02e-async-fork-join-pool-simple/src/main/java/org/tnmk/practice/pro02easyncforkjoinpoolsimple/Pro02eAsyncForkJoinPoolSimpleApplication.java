package org.tnmk.practice.pro02easyncforkjoinpoolsimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Pro02eAsyncForkJoinPoolSimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(Pro02eAsyncForkJoinPoolSimpleApplication.class, args);
    }

}
