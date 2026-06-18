package com.example.sipmfsimulatorbackend.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient navWebClient() {
        HttpClient httpClient = HttpClient.create().responseTimeout(Duration.ofMinutes(3));
        // Configure the underlying Netty HttpClient for timeouts
//        HttpClient httpClient = HttpClient.create().option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // Connection timeout
//                .responseTimeout(Duration.ofMillis(5000))          // Response timeout
//                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5, TimeUnit.SECONDS)).addHandlerLast(new WriteTimeoutHandler(5, TimeUnit.SECONDS)));
        return WebClient.builder().baseUrl("https://api.mfapi.in").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
