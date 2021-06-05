package org.tnmk.practice.pro04zuulproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class Pro04ZuulProxyApplication {
  public static void main(String[] args) {
    SpringApplication.run(Pro04ZuulProxyApplication.class, args);
  }
}
