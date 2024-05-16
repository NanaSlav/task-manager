/**
 * Created on 24/03/2024
 */
package ru.nanaslav.tasksmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.tasksmicroservice.domain.model.Task;
import ru.nanaslav.tasksmicroservice.domain.dto.TaskRequest;
import ru.nanaslav.tasksmicroservice.repository.TaskRepository;
import ru.nanaslav.tasksmicroservice.service.TaskService;

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

    @Autowired
    TaskService taskService;
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

    /**
     * Создание задачи
     *
     * @param taskRequest
     * @return
     */
    @GetMapping("/create")
    // TODO это пока тестовый вариант для проверки взаимодействия микросервисов, потом нужно доделать
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest, @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(taskService.createTask(taskRequest.getTitle(), token));
    }
}
