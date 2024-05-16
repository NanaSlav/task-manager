/**
 * Created on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.tasksmicroservice.domain.model.Difficulty;
import ru.nanaslav.tasksmicroservice.domain.model.Priority;
import ru.nanaslav.tasksmicroservice.domain.model.Status;
import ru.nanaslav.tasksmicroservice.domain.model.Task;
import ru.nanaslav.tasksmicroservice.service.TaskService;

/**
 * Контроллер для работы с задачами
 *
 * @author nana
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestHeader("Authorization") String token, @RequestBody Task task) {
        return new ResponseEntity<Task>(taskService.createTask(task, token), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestHeader("Authorization") String token, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(task, token));
    }

    @PostMapping("/update/status")
    public ResponseEntity<Task> updateTaskStatus(@RequestHeader("Authorization") String token,
                                                 @RequestParam String taskId,
                                                 @RequestParam Status status) {
        return ResponseEntity.ok(taskService.updateStatus(taskId, status, token));
    }

    @PostMapping("/update/description")
    public ResponseEntity<Task> updateDescription(@RequestHeader("Authorization") String token,
                                                  @RequestParam String taskId,
                                                  @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateDescription(taskId, task.getDescription(), token));
    }

    @PostMapping("/update/deadline")
    public ResponseEntity<Task> updateDeadline(@RequestHeader("Authorization") String token,
                                               @RequestParam String taskId,
                                               @RequestBody Task task) {
        return ResponseEntity.ok(taskService.changeDeadline(taskId, task.getDeadline(), token));
    }

    @PostMapping("/update/assignee")
    public ResponseEntity<Task> changeAssignee(@RequestHeader("Authorization") String token,
                                               @RequestParam String taskId,
                                               @RequestParam String assignee) {
        return ResponseEntity.ok(taskService.changeAssignee(taskId, assignee, token));
    }

    @PostMapping("/update/priority")
    public ResponseEntity<Task> changePriority(@RequestHeader("Authorization") String token,
                                               @RequestParam String taskId,
                                               @RequestParam Priority priority) {
        return ResponseEntity.ok(taskService.changePriority(taskId, priority, token));
    }

    @PostMapping("/update/difficulty")
    public ResponseEntity<Task> changeDifficulty(@RequestHeader("Authorization") String token,
                                                 @RequestParam String taskId,
                                                 @RequestParam Difficulty difficulty) {
        return ResponseEntity.ok(taskService.changeDifficulty(taskId, difficulty, token));
    }
}
