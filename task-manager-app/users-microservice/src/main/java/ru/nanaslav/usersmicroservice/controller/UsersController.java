/**
 * Created on 24/03/2024
 */
package ru.nanaslav.usersmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.usersmicroservice.domain.dto.UserDTO;
import ru.nanaslav.usersmicroservice.domain.model.User;
import ru.nanaslav.usersmicroservice.repository.UserRepository;
import ru.nanaslav.usersmicroservice.service.UserService;

/**
 * Контроллер для обработки запросов о пользователях
 *
 * @author nana
 * @version 1.0.0
 */

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    /**
     * Получение пользователя по имени
     *
     * @param username имя пользователя
     * @return {@link ResponseEntity<UserDTO>}
     */
    @GetMapping("/")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestBody String username) {
        return ResponseEntity.ok(userService.getUserProfileByUsername(username));
    }

    /**
     * Получение текущего авторизованного пользователя
     *
     * @return {@link ResponseEntity<UserDTO>} пользователь
     */
    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUserPofile());
    }


    /**
     * Получение имени текущего авторизованного пользователя
     *
     * @return имя пользователя
     */
    @GetMapping("/current-username")
    public ResponseEntity<String> getCurrentUsername() {
        return ResponseEntity.ok(userService.getCurrentUserName());
    }

    /**
     * Редактирование профиля пользователя
     *
     * @param user измененный профиль пользователя
     * @return пользователь
     */
    @PostMapping("/update")
    public ResponseEntity<UserDTO> updateUserProfile(UserDTO user) {
        return ResponseEntity.ok(userService.updateUserProfile(user));
    }

    /**
     * Смена пароля
     *
     * @param oldPassword старый пароль
     * @param newPassword новый пароль
     * @return пользователь
     */
    @PostMapping("/update/password")
    public ResponseEntity<User> changePassword(String oldPassword, String newPassword) {
        return ResponseEntity.ok(userService.changePassword(oldPassword, newPassword));
    }
}
