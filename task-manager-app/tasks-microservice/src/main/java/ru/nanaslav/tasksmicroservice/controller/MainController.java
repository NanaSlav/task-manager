/**
 * Created on 24/03/2024
 */
package ru.nanaslav.tasksmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nanaslav.tasksmicroservice.model.Task;
import ru.nanaslav.tasksmicroservice.repository.TaskRepository;

/**
 * Главный контроллер для задач
 *
 * @author nana
 * @version 0.0.1
 */
@RestController
@RequestMapping("/tasks")
public class MainController {
    @Autowired
    TaskRepository taskRepository;

    /**
     * Тестовый метод home
     *
     * @return {@link String}
     */
    @GetMapping("/home")
    public String home() {
        // Тестовый код для БД
        Task task = new Task("task1");
        task.setStatus("open");
        taskRepository.insert(task);

        return "home";
    }

}
