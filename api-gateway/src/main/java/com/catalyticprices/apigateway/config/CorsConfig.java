package com.catalyticprices.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;

@Configuration
public class CorsConfig extends CorsConfiguration {


    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        ArrayList<String> origins = new ArrayList<>();
        origins.add("*"); // todo change it to domain.

        ArrayList<String> methods = new ArrayList<>();
        methods.add("GET");
        methods.add("POST");
        methods.add("PUT");
        methods.add("DELETE");
        methods.add("OPTIONS");
        methods.add("HEAD");

        ArrayList<String> headers = new ArrayList<>();
        headers.add("origin");
        headers.add("content-type");
        headers.add("accept");
        headers.add("authorization");
        headers.add("cookie");
        headers.add("lang");
        headers.add("provider");
        headers.add("currency");
        headers.add("Currency");
        headers.add("Authorization");
        headers.add("Access-Control-Allow-Headers");
        headers.add("Access-Control-Allow-Origin");
        headers.add("Referer");
        headers.add("sec-ch-ua");
        headers.add("sec-ch-ua-mobile");
        headers.add("sec-ch-ua-platform");
        headers.add("User-Agent");

        config.setAllowedMethods(methods);
        config.setAllowedHeaders(headers);
        config.setAllowCredentials(false);
        config.setAllowedOriginPatterns(origins);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}