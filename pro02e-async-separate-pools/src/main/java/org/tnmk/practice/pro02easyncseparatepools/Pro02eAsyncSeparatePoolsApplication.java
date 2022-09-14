package org.tnmk.practice.pro02easyncseparatepools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Pro02eAsyncSeparatePoolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(Pro02eAsyncSeparatePoolsApplication.class, args);
    }

}
