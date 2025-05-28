package com.example.demogame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoGameApplication.class, args);
    }

}
