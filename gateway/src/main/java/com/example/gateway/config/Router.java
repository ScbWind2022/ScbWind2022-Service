package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Router {
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
                .route(r -> r
                        .path(urlV1 + "/trade/operation")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/trade/operation/list")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/trade/session")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/accounts/sum")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/rate/current")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/rate/current")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/rate/current/list")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/rate/range")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/banned")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/rate/users/enable")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/get/banned")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/get/enabled")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/removebanned")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/check/enable")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/login")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/profile")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/profile/checks")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/profile/user")
                        .uri(authService))
                .route(r -> r
                        .path(urlV1 + "/users/register")
                        .uri(authService))
                .route(r -> r
                        .path("/users/update-token")
                        .uri(authService))
                .build();
    }
}
