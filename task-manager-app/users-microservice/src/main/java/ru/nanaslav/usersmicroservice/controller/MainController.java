/**
 * Created on 24/03/2024
 */
package ru.nanaslav.usersmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.usersmicroservice.model.User;
import ru.nanaslav.usersmicroservice.repository.UserRepository;
import ru.nanaslav.usersmicroservice.service.UserService;

/**
 * Главный контроллер пользователей
 *
 * @author nana
 * @version 1.0.0
 */

// TODO его нужно будеть поделить...
@RestController
@RequestMapping("/users")
public class MainController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    /**
     * Тестовый метод home
     *
     * @return {@link String}
     */
    @GetMapping("/home")
    public String home() {
        User user = new User("nanaslav", "123");
        userRepository.insert(user);
        return "users - home";
    }

    /**
     * Создание нового пользователя
     *
     * @param user пользователь
     * @return {@link ResponseEntity}
     */
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<User> getUser(@RequestBody String username) {
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
}
