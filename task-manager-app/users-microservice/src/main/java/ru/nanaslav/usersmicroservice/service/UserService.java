/**
 * Created on 27/04/2024
 */
package ru.nanaslav.usersmicroservice.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nanaslav.usersmicroservice.domain.model.User;
import ru.nanaslav.usersmicroservice.repository.UserRepository;

/**
 * Сервис для работы с пользователями
 *
 * @author nana
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Создание нового пользователя
     *
     * @param username имя пользователя
     * @param password пароль
     *
     * @return {@link User} пользователь
     */
    public User createUser(String username, String password) {
        User user = User.builder()
                .username(username)
                .password(bCryptPasswordEncoder().encode(password))
                .build();
        return createUser(user);
    }

    /**
     * Создание нового пользователя
     *
     * @param user пользователь
     * @return {@link User}
     */

    public User createUser (User user) {
        if (!isUserExists(user)) {
            return userRepository.insert(user);
        } else {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    /**
     * Проверка наличия пользователя в системе
     * @param user Пользователь
     * @return {@link boolean}
     */
    private boolean isUserExists(@NotNull User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUserByUsername(auth.getName());
    }

    public String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
