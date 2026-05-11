package com.example.soul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SoulApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoulApplication.class, args);
    }

}
