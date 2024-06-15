package com.sifts.API.Gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CustomErrorFilter implements GatewayFilter, Ordered {
    private final static Logger LOGGER = Logger.getLogger(CustomErrorFilter.class.getName());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .onErrorResume(Exception.class, ex -> handleException(ex, exchange.getResponse()));
    }

    private Mono<Void> handleException(Throwable ex, ServerHttpResponse response) {
        LOGGER.info(Level.SEVERE+ ex.getMessage());
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return response.setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}