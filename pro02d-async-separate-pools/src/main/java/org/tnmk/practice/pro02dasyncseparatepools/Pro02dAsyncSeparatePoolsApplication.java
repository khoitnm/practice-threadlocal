package org.tnmk.practice.pro02dasyncseparatepools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Pro02dAsyncSeparatePoolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(Pro02dAsyncSeparatePoolsApplication.class, args);
    }

}
