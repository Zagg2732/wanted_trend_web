package com.wanted.wantedtrend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WantedTrendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WantedTrendApplication.class, args);
    }

}
