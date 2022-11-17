package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ROUTER {
    private final String urlV1 = "/api/v1";
    private final String authService = "lb://authservice";
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                .route(r -> r
                        .path(urlV1 + "/user/login")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/user/register")
                        .uri(authService))
                .build();
    }
}
