/**
 * Created on 20/05/2024
 */
package ru.nanaslav.projectsmicroservice.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Модель проекта
 *
 * @author nana
 */
@Document
@Data
@Builder
public class Project {
    /**
     * id проекта
     */
    @Id
    private String id;
    /**
     * Название проекта
     */
    private String title;

    /**
     * Описание проекта
     */
    private String description;

    /**
     * Список задач - идентификаторы
     */
    private Set<String> tasks;

    /**
     * Участники проекта - список имен пользователей
     */
    private Set<String> participants;

    /**
     * Добавить задачу в проект
     * @param taskId id задачи
     */
    public void addTask(String taskId) {
        if (tasks == null) {
            tasks = new HashSet<>();
        }
        tasks.add(taskId);
    }

    /**
     * Удалить задачу из проекта
     * @param taskId id задачи
     * @return true если задача удалена, false, если задача отсутсвует в проекте
     */
    public boolean deleteTask(String taskId) {
        return tasks != null && tasks.remove(taskId);
    }

    /**
     * Добавить участника в проект
     * @param username имя нового участника
     */
    public void addParticipant(String username) {
        if (participants == null) {
            participants = new HashSet<>();
        }
        participants.add(username);
    }


    /**
     * Удаление участника из проекта
     * @param username имя удаляемого участника
     * @return true если участник удален
     */
    public boolean deleteParticipant(String username) {
        return participants != null && participants.remove(username);
    }
}