package com.catalyticprices.apigateway.config;

import com.catalyticprices.apigateway.service.JWTService;
import com.catalyticprices.apigateway.service.impl.JWTServiceImpl;
import com.catalyticprices.apigateway.util.JwtUtil;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
public class SecuredFilter implements GlobalFilter {

    @Autowired
    private JWTService jwtService;

    public static final List<String> securedEndpoint = ImmutableList.of(
            "/account",
            "/statement"
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        securedEndpoint.forEach(i -> {
            String request = exchange.getRequest().getURI().toString();
            if (request.contains(i)) {
                String tokenHeader = exchange.getRequest()
                        .getHeaders()
                        .getFirst("Authorization");
                this.jwtService.validate(tokenHeader); // TODO SHOULD BE ENABLED
               // JwtUtil.jwtValidator(exchange.getRequest());
            }
        });
        return chain.filter(exchange);
    }
}
