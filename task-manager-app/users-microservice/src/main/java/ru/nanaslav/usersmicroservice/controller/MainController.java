/**
 * Created on 24/03/2024
 */
package ru.nanaslav.usersmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Главный контроллер пользователей
 *
 * @author nana
 * @version 1.0.0
 */
@RestController
@RequestMapping("/users")
public class MainController {
    /**
     * Тестовый метод home
     *
     * @return {@link String}
     */
    @GetMapping("/home")
    public String home() {
        return "users - home";
    }
}
