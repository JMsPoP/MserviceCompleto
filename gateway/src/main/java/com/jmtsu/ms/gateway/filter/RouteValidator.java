package com.jmtsu.ms.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/signup",
            "/auth/login",
<<<<<<< HEAD
            "/eureka",
            "/resilience/**",
            "/product",
            "/product/findOne"
=======
            "/eureka"
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}