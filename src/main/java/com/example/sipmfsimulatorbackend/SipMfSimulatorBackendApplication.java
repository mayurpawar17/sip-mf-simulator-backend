package com.example.sipmfsimulatorbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class SipMfSimulatorBackendApplication {

    @PostConstruct
    public void init() {
        // Explicitly set JVM timezone to the modern IANA standard
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SipMfSimulatorBackendApplication.class, args);
    }

}
