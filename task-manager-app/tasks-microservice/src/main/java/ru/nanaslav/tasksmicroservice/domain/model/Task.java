/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Модель задачи
 *
 * @author nana
 * @version 1.0.0
 */
@Document("tasks")
@Data
@Builder
public class Task {
    public Task(String title) {
        this.title = title;
    }
    @Id
    private String Id;
    /**
     * Название задачи
     */
    private String title;
    /**
     * Проект, в который включена задача
     */
    private String project;
    /**
     * Статус задачи
     */
    private String status;
    /**
     * Автор задачи
     */
    private String author;

    /**
     * Приоритет выполнения
     */
    private Priority priority;

    /**
     * Оценка сложности задачи
     */
    private Difficulty difficulty;

    /**
     * Исполнитель задачи
     */
    private String assignee;

    /**
     * Описание задачи
     */
    private String description;

    /**
     * История изменений
     */
    private List<HistoryEvent> history;
    /**
     * Комментарии к задаче
     */
    private List<Comment> comments;
}
