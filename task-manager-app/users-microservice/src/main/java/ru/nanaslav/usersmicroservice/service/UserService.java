/**
 * Created on 27/04/2024
 */
package ru.nanaslav.usersmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nanaslav.usersmicroservice.model.User;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Создание нового пользователя
     *
     * @param username имя пользователя
     * @param password пароль
     *
     * @return {@link User} пользователь
     */
    public User createUser(String username, String password) {
        // TODO возможно стоит сделать шифрование пароля сразу на стороне фронта?
        User user = new User(username, bCryptPasswordEncoder.encode(password));
        userRepository.insert(user);
        return user;
    }

    /**
     * Создание нового пользователя
     *
     * @param user пользователь
     * @return {@link User}
     */

    public User createUser (User user) {
        // TODO здесь должна быть куча проверок, но пока оставлю так
        return userRepository.insert(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
