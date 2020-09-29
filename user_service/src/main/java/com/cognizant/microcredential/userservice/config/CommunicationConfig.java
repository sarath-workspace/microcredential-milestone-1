package com.cognizant.microcredential.userservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CommunicationConfig {

    @Bean
    @LoadBalanced
    public WebClient.Builder template() {
        return WebClient.builder();
    }
}
