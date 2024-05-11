/**
 * Created on 24/03/2024
 */
package ru.nanaslav.usersmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
     * @return {@link ResponseEntity<User>}
     */
    @GetMapping("/")
    public ResponseEntity<User> getUserByUsername(@RequestBody String username) {
        // TODO надо сделать по человечески...
        // User user = (User) userService.loadUserByUsername(username);
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            // TODO тут бы тоже не с 404 возвращать..
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Получение текущего авторизованного пользователя
     *
     * @return {@link ResponseEntity<User>} пользователь
     */
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/current-username")
    public ResponseEntity<String> getCurrentUsername() {
        return ResponseEntity.ok(userService.getCurrentUserName());
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
