package org.tnmk.practice.pro02gasyncvirtualthreadsimple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Pro02gAsyncVirtualThreadSimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(Pro02gAsyncVirtualThreadSimpleApplication.class, args);
    }

}
