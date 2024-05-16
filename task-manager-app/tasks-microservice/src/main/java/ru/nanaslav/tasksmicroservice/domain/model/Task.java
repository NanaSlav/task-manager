/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
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
    private Status status;
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
     * Срок выполнения задачи
     */
    private Date deadline;

    /**
     * История изменений
     */
    private List<HistoryEvent> history = new ArrayList<>();
    /**
     * Комментарии к задаче
     */
    private List<Comment> comments = new ArrayList<>();

    /**
     * Добавление события в историю
     * @param event {@link HistoryEvent}
     */
    public void addHistoryEvent(HistoryEvent event) {
        history.add(event);
    }

    /**
     * Добавление комментария
     * @param comment {@link Comment}
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }
}