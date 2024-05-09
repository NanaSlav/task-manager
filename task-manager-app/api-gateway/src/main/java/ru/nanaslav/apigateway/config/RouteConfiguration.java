/**
 * Created on 09/05/2024
 */
package ru.nanaslav.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурации путей api
 *
 * @author nana
 */
@Configuration
public class RouteConfiguration {
    // TODO добавить фильры для аутентификации
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-routes", r -> r.path("/auth/**")
                        .uri("lb://users-microservice"))
                .route("users-routes", r -> r.path("/users/**")
                        .uri("lb://users-microservice"))
                .route("tasks-routes", r -> r.path("/tasks/**")
                        .uri("lb://tasks-microservice"))
                .route("project_routes", r-> r.path("projects/**")
                        .uri("lb://projects-microservice"))
                .build();
    }
}
