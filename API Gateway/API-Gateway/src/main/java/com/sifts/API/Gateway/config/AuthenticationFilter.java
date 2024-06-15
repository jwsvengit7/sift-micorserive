package com.sifts.API.Gateway.config;

import com.sifts.API.Gateway.config.APIGatewayRouteValidate;
import com.sifts.API.Gateway.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private static final Logger Logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private APIGatewayRouteValidate validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {

            if (validator.isSecured().test(exchange.getRequest())) {
                Logger.info("Incoming request to secured endpoint: {}", exchange.getRequest().getURI());
                HttpHeaders headers = exchange.getRequest().getHeaders();
                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                    Logger.warn("Missing authorization header");
                    throw new RuntimeException("Missing authorization header");
                }

                String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
//                    if(exchange.getRequest().getURI().equals(""))
                    Logger.info("API {}",exchange.getRequest().getURI());
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    Logger.warn("Invalid access...!");
                    throw new RuntimeException("Unauthorized access to application");
                }


                exchange.getRequest().mutate()
                        .header("APPID", "SIFTS")
                        .build();
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}