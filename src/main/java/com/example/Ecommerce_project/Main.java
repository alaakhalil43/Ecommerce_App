package com.example.Ecommerce_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.example.Ecommerce_project.Models")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
