package com.catalyticprices.apigateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadBalanceRoutesConfig {

    @Bean
    public RouteLocator loadBalanceRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/identity/**")
                        .uri("lb://identity/**"))
                .route(p -> p.path("/account/**")
                        .uri("lb://account/**"))
                .route(p -> p.path("/statement/**")
                        .uri("lb://statement/**"))
                .build();
    }
}
