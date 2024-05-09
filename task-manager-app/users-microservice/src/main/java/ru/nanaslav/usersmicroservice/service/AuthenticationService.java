/**
 * Created on 08/05/2024
 */
package ru.nanaslav.usersmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nanaslav.usersmicroservice.domain.dto.JwtAuthenticationResponse;
import ru.nanaslav.usersmicroservice.domain.dto.SignInRequest;
import ru.nanaslav.usersmicroservice.domain.dto.SignUpRequest;
import ru.nanaslav.usersmicroservice.domain.model.User;

/**
 * Сервис аутентификации
 *
 * @author nana
 */
@Service
public class AuthenticationService {
    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return {@link JwtAuthenticationResponse}
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        userService.createUser(user);

        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return {@link JwtAuthenticationResponse}
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        var user = userService.loadUserByUsername(request.getUsername());
        String jwt = jwtService.generateToken( user);
        return new JwtAuthenticationResponse(jwt);
    }
}
