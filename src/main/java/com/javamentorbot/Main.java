package com.javamentorbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        
        SpringApplication.run(Main.class, args);
        System.out.println("--------------------------------------------------");
        System.out.println("   Server Started! Open browser at: http://localhost:8080");
        System.out.println("--------------------------------------------------");
    }
}