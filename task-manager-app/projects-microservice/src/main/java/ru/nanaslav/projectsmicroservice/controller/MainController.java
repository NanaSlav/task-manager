/**
 * Created on 24/03/2024
 */
package ru.nanaslav.projectsmicroservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Главный контроллер для проектов
 *
 * @author nana
 * @version 0.0.1
 */
@RestController
@RequestMapping("/projects")
public class MainController {

    /**
     * Тестовый метод home
     *
     * @return {@link String}
     */
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
