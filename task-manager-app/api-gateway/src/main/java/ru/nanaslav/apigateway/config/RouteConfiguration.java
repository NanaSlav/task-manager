/**
 * Created on 09/05/2024
 */
package ru.nanaslav.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nanaslav.apigateway.filter.AuthenticationFilter;

/**
 * Конфигурации путей api
 *
 * @author nana
 */
@Configuration
public class RouteConfiguration {
    @Autowired
    AuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-routes", r -> r.path("/auth/**")
                        .uri("lb://users-microservice"))
                .route("users-routes", r -> r.path("/users/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://users-microservice"))
                .route("tasks-routes", r -> r.path("/tasks/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://tasks-microservice"))
                .route("project_routes", r-> r.path("/projects/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://projects-microservice"))
                .route("notification-routes", r -> r.path("/notification/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://notification-microservice"))
                .build();
    }
}
