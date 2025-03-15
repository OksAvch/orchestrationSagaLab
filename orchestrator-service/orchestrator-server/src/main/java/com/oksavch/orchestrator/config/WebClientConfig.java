package com.oksavch.orchestrator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean("paymentClient")
    public WebClient paymentClient(@Value("${service.endpoints.payment}") String endpoint) {
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }

    @Bean("inventoryClient")
    public WebClient inventoryClient(@Value("${service.endpoints.inventory}") String endpoint) {
        return WebClient.builder()
                .baseUrl(endpoint)
                .build();
    }
}
