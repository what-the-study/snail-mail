package com.snailmail.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SnailMailBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnailMailBackApplication.class, args);
    }

}
