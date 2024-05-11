/**
 * Created on 09/05/2024
 */
package ru.nanaslav.usersmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.usersmicroservice.domain.dto.JwtAuthenticationResponse;
import ru.nanaslav.usersmicroservice.domain.dto.SignInRequest;
import ru.nanaslav.usersmicroservice.domain.dto.SignUpRequest;
import ru.nanaslav.usersmicroservice.service.AuthenticationService;

/**
 * Контроллер аутентификации
 *
 * @author nana
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    /**
     * Регистрация пользователя
     *
     * @param request {@link SignUpRequest}
     * @return {@link JwtAuthenticationResponse}
     */
    @PostMapping("/sign-up")
    public JwtAuthenticationResponse signUp(@RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    /**
     * Авторизация пользователя
     *
     * @param request {@link SignInRequest}
     * @return {@link JwtAuthenticationResponse}
     */
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
