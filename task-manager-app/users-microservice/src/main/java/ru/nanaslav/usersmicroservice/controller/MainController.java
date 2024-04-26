/**
 * Created on 24/03/2024
 */
package ru.nanaslav.usersmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nanaslav.usersmicroservice.model.User;
import ru.nanaslav.usersmicroservice.repository.UserRepository;

/**
 * Главный контроллер пользователей
 *
 * @author nana
 * @version 1.0.0
 */
@RestController
@RequestMapping("/users")
public class MainController {
    @Autowired
    UserRepository userRepository;

    /**
     * Тестовый метод home
     *
     * @return {@link String}
     */
    @GetMapping("/home")
    public String home() {
        User user = new User("nanaslav");
        userRepository.insert(user);
        return "users - home";
    }
}
