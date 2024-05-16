/**
 * Created on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.tasksmicroservice.domain.model.*;
import ru.nanaslav.tasksmicroservice.service.PaginationService;
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

    @Autowired
    PaginationService paginationService;

    /**
     * Создание новой задачи
     *
     * @param token токен пользователя
     * @param task задача
     * @return созданная задача
     */
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestHeader("Authorization") String token, @RequestBody Task task) {
        return new ResponseEntity<Task>(taskService.createTask(task, token), HttpStatus.CREATED);
    }

    /**
     * Изменение задачи
     *
     * @param token токен пользователя
     * @param task измененная задача
     * @return измененная задача
     */
    @PostMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestHeader("Authorization") String token, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateTask(task, token));
    }

    /**
     * Изменение статуса задачи
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param status новый статус задачи
     * @return измененная задача
     */
    @PostMapping("/update/status")
    public ResponseEntity<Task> updateTaskStatus(@RequestHeader("Authorization") String token,
                                                 @RequestParam String taskId,
                                                 @RequestParam Status status) {
        return ResponseEntity.ok(taskService.updateStatus(taskId, status, token));
    }

    /**
     * Изменение описание задачи
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param task задача
     * @return измененная задача
     */
    @PostMapping("/update/description")
    public ResponseEntity<Task> updateDescription(@RequestHeader("Authorization") String token,
                                                  @RequestParam String taskId,
                                                  @RequestBody Task task) {
        return ResponseEntity.ok(taskService.updateDescription(taskId, task.getDescription(), token));
    }

    /**
     * Изменение срока выполнения задачи
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param task задача
     * @return измененная задача
     */
    @PostMapping("/update/deadline")
    public ResponseEntity<Task> updateDeadline(@RequestHeader("Authorization") String token,
                                               @RequestParam String taskId,
                                               @RequestBody Task task) {
        return ResponseEntity.ok(taskService.changeDeadline(taskId, task.getDeadline(), token));
    }

    /**
     * Назначение нового исполнителя
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param assignee имя нового исполнителя
     * @return измененная задача
     */
    @PostMapping("/update/assignee")
    public ResponseEntity<Task> changeAssignee(@RequestHeader("Authorization") String token,
                                               @RequestParam String taskId,
                                               @RequestParam String assignee) {
        return ResponseEntity.ok(taskService.changeAssignee(taskId, assignee, token));
    }

    /**
     * Изменение приоритета выполнения
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param priority приоритет выполнения
     * @return измененная задача
     */
    @PostMapping("/update/priority")
    public ResponseEntity<Task> changePriority(@RequestHeader("Authorization") String token,
                                               @RequestParam String taskId,
                                               @RequestParam Priority priority) {
        return ResponseEntity.ok(taskService.changePriority(taskId, priority, token));
    }

    /**
     * Изменение оценки сложности задачи
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param difficulty оценка сложности задачи
     * @return измененная задача
     */
    @PostMapping("/update/difficulty")
    public ResponseEntity<Task> changeDifficulty(@RequestHeader("Authorization") String token,
                                                 @RequestParam String taskId,
                                                 @RequestParam Difficulty difficulty) {
        return ResponseEntity.ok(taskService.changeDifficulty(taskId, difficulty, token));
    }

    /**
     * Удаление задачи
     *
     * @param taskId id задачи
     * @return {@link HttpStatusCode}
     */
    @PostMapping("/delete")
    public HttpStatusCode delete(@RequestParam String taskId) {
        taskService.deleteTask(taskId);
        return HttpStatus.OK;
    }

    /**
     * Получение задачи по id
     * @param taskId id задачи
     * @return задача
     */
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable String taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    /**
     * Получение всех доступных задач
     *
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/all")
    public ResponseEntity<Page<Task>> getAllTasks(@RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.getAllTasks(), page, size);
        return ResponseEntity.ok(tasksPage);
    }

    /**
     * Получение всех задач в проекте
     *
     * @param project проект
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/project")
    public ResponseEntity<Page<Task>> getTasksInProject(@RequestParam("project") String project,
                                                       @RequestParam("page") int page,
                                                       @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.getAllTasksInProject(project), page,size);
        return ResponseEntity.ok(tasksPage);
    }

    /**
     * Получение всех назначенных задач
     *
     * @param token токен пользователя
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/assigned")
    public ResponseEntity<Page<Task>> getAssignedTasks(@RequestHeader("Authorization") String token,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.getAssignedTasks(token), page, size);
        return ResponseEntity.ok(tasksPage);
    }

    /**
     * Получение открытых задач, назначенных на текущего пользователя
     *
     * @param token токен пользователя
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/assigned-and-opened")
    public ResponseEntity<Page<Task>> getAssignedAndOpenedTasks(@RequestHeader("Authorization") String token,
                                                                @RequestParam("page") int page,
                                                                @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.getOpenedAndAssignedTasks(token), page, size);
        return ResponseEntity.ok(tasksPage);
    }

    /**
     * Получение все задач, созданных текущим пользователем
     *
     * @param token токен пользователя
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/created")
    public ResponseEntity<Page<Task>> getCreatedTasks(@RequestHeader("Authorization") String token,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.getCreatedTasks(token), page, size);
        return ResponseEntity.ok(tasksPage);
    }

    /**
     * Поиск по всем доступным задачам
     *
     * @param query поисковый запрос
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/search")
    public ResponseEntity<Page<Task>> searchTasks(@RequestParam("q") String query,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.searchTasks(query), page, size);
        return ResponseEntity.ok(tasksPage);
    }

    /**
     * Поиск задач по проекту
     *
     * @param query поисковый запрос
     * @param project проект
     * @param page номер страницы
     * @param size размер страницы
     * @return страница с задачами
     */
    @GetMapping("/search-in-project")
    public ResponseEntity<Page<Task>> searchTaskInProject(@RequestParam("q") String query,
                                                          @RequestParam("project") String project,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        Page<Task> tasksPage = paginationService.getPage(taskService.searchTasksInProject(query, project), page, size);
        return ResponseEntity.ok(tasksPage);
    }


    /**
     * Создание комментария к задаче
     *
     * @param token токен пользователя
     * @param taskId id задачи
     * @param comment комментарий
     * @return измененная задача
     */
    @PostMapping("/comment/{taskId}")
    public ResponseEntity<Task> addComment(@RequestHeader("Authorization") String token,
                                              @PathVariable String taskId,
                                              @RequestBody Comment comment) {
        return ResponseEntity.ok(taskService.addComment(taskId, comment.getText(), token));
    }
}