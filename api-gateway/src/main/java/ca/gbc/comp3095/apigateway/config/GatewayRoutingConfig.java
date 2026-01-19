package ca.gbc.comp3095.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutingConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Course Service
                .route("course-service", r -> r.path("/api/v1/courses/**")
                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers.Authorization}"))
                        .uri("http://course-service:8080"))
                // Grade Service
                .route("grade-service", r -> r.path("/api/v1/grades/**")
                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers.Authorization}"))
                        .uri("http://grade-service:8081"))
                // Reminder Service
                .route("reminder-service", r -> r.path("/api/v1/reminders/**")
                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers.Authorization}"))
                        .uri("http://reminder-service:8082"))
                // Task Service
                .route("task-service", r -> r.path("/api/v1/tasks/**")
                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers.Authorization}"))
                        .uri("http://task-service:8083"))
                // User Service
                .route("user-service", r -> r.path("/api/v1/users/**")
                        .filters(f -> f.addRequestHeader("Authorization", "#{request.headers.Authorization}"))
                        .uri("http://user-service:8089"))
                .build();
    }

}
