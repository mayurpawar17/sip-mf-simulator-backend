package com.example.sipmfsimulatorbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SipMfSimulatorBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SipMfSimulatorBackendApplication.class, args);
    }
}
