package com.lazarev.analyticsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AnalyticsServiceApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(AnalyticsServiceApplicationMain.class, args);
    }
}
