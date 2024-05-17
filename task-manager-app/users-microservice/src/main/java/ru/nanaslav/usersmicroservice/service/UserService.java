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
import ru.nanaslav.usersmicroservice.domain.dto.UserDTO;
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
    private User createUser(String username, String password) {
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

    /**
     * Получение пользователя по имени
     *
     * @param username имя пользователя
     * @return {@link User}
     */
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

    /**
     * Получение текущего авторизованного пользователя
     *
     * @return {@link User}
     */
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUserByUsername(auth.getName());
    }

    /**
     * Получение имени текущего авторизованного пользователя
     *
     * @return имя пользователя
     */
    public String getCurrentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    /**
     * Получение профиля текущего пользователя
     *
     * @return {@link UserDTO}
     */
    public UserDTO getCurrentUserPofile() {
        return new UserDTO(getCurrentUser());
    }



    /**
     * Изменить пароль текущего пользователя
     *
     * @param oldPassword старый пароль
     * @param newPassword новый пароль
     *
     * @return {@link User} пользователь
     */
    public User changePassword(String oldPassword, String newPassword) {
        User user = getCurrentUser();
        // Перед сменой пароля нужно проверить текущий
        if (bCryptPasswordEncoder().encode(oldPassword).equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);
        }
        return user;
    }

    /**
     * Редактирование профиля пользователя
     *
     * @param changedUser Измененный пользователь
     * @return {@link UserDTO}
     */
    public UserDTO updateUserProfile(UserDTO changedUser) {
        User user = userRepository.findByUsername(changedUser.getUsername());
        user.setAvatar(changedUser.getAvatar());
        user.setBio(changedUser.getBio());
        user.setQualification(changedUser.getQualification());
        userRepository.save(user);
        return new UserDTO(user);
    }

    /**
     * Получение профиля пользователя по его имени
     *
     * @param username имя пользователя
     * @return {@link UserDTO}
     */
    public UserDTO getUserProfileByUsername(String username) {
        return new UserDTO(getUserByUsername(username));
    }
}
