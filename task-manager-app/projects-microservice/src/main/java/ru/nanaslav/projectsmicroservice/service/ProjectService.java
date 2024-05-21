/**
 * Created on 20/05/2024
 */
package ru.nanaslav.projectsmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nanaslav.projectsmicroservice.domain.model.Project;
import ru.nanaslav.projectsmicroservice.proxy.UsersProxy;
import ru.nanaslav.projectsmicroservice.repository.ProjectRepository;

/**
 * Сервис для работы с проектами
 *
 * @author nana
 */
@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UsersProxy usersProxy;

    /**
     * Создание нового проекта
     *
     * @param project  модель проекта
     * @param token токен текущего пользователя
     * @return созданный проект
     */
    public Project createProject(Project project, String token) {
        // Текущего пользователя нужно сделать участником проекта
        project.addParticipant(usersProxy.getCurrentUsername(token).getBody());
        return projectRepository.insert(project);
    }

    /**
     * Добавление задачи в проект
     *
     * @param projectId id проекта
     * @param taskId id задачи
     * @return {@link Project}
     */
    public Project addTask(String projectId, String taskId) {
        Project project = projectRepository.findProjectById(projectId);
        if (project != null) {
            project.addTask(taskId);
            projectRepository.save(project);
        }
        return project;
    }

    /**
     * Добавление участника в проект
     *
     * @param username имя пользователя, добавляемого в проект
     * @param projectId id проекта
     * @return {@link Project}
     */
    public Project addParticipant(String username, String projectId) {
        Project project = projectRepository.findProjectById(projectId);
        if (project != null) {
            project.addParticipant(username);
            projectRepository.save(project);
        }
        return project;
    }

    /**
     * Редактирование проекта
     *
     * @param updatedProject измененный проект
     * @return {@link Project}
     */
    public Project updateProject(Project updatedProject) {
        Project project = projectRepository.findProjectById(updatedProject.getId());
        if (project != null) {
            projectRepository.save(updatedProject);
        }
        return updatedProject;
    }


}
