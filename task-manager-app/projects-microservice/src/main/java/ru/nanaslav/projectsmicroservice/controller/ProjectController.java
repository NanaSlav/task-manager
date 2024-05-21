/**
 * Created on 21/05/2024
 */
package ru.nanaslav.projectsmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nanaslav.projectsmicroservice.domain.model.Project;
import ru.nanaslav.projectsmicroservice.service.ProjectService;

/**
 * Контроллер для работы с проектами
 *
 * @author nana
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    /**
     * Создание нового проекта
     *
     * @param token токен текущего пользователя
     * @param project проект
     * @return созданный проект
     */
    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestHeader("Authorization") String token,
                                                 @RequestBody Project project) {
        return ResponseEntity.ok(projectService.createProject(project, token));
    }

    /**
     * Добавить нового участника в проект
     *
     * @param token токен текущего пользователя
     * @param projectId id проекта
     * @param username имя добавляемого пользователя
     * @return проект
     */
    @PostMapping("/{projectId}/add/participant")
    public ResponseEntity<Project> addParticipant(@RequestHeader("Authorization") String token,
                                                  @PathVariable String projectId,
                                                  @RequestParam String username) {
        return ResponseEntity.ok(projectService.addParticipant(username, projectId));
    }

    /**
     * Добавление задачи в проект
     *
     * @param token токен текущего пользователя
     * @param projectId id проекта
     * @param taskId id задачи
     * @return проект
     */
    @PostMapping("/{projectId}/add/task")
    public ResponseEntity<Project> addTask(@RequestHeader("Authorization") String token,
                                           @PathVariable String projectId,
                                           @RequestParam String taskId) {
        return ResponseEntity.ok(projectService.addTask(projectId, taskId));
    }

    /**
     * Редактирование проекта
     *
     * @param token токен пользователя
     * @param project измененный проект
     * @return проект
     */
    @PostMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestHeader("Authorization") String token,
                                                 @RequestBody Project project) {
        return ResponseEntity.ok(projectService.updateProject(project));
    }
}
