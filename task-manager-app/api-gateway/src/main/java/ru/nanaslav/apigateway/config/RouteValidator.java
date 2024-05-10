/**
 * Created on 09/05/2024
 */
package ru.nanaslav.apigateway.config;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 * Класс для валидации маршрутов
 *
 * @author nana
 */
@Component
public class RouteValidator {
    public static final List<String> openApiEndpoints= List.of(
            "/auth/sign-up",
            "/auth/sign-in"
    );

    public Predicate<HttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
