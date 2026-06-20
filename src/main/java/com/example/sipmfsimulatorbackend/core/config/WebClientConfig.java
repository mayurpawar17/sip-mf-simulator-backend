package com.example.sipmfsimulatorbackend.core.config;


import io.netty.handler.ssl.SslHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.SslProvider;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient navWebClient() {

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMinutes(3))
                .secure() // Activates default TLS/SSL configuration
                .doOnConnected(connection -> {
                    // Dynamically look up the SslHandler in Netty's pipeline and apply timeout
                    SslHandler sslHandler = connection.channel().pipeline().get(SslHandler.class);
                    if (sslHandler != null) {
                        sslHandler.setHandshakeTimeout(30, TimeUnit.SECONDS);
                    }
                });      return WebClient.builder().baseUrl("https://api.mfapi.in").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).clientConnector(new ReactorClientHttpConnector(httpClient)).build();
    }
}
