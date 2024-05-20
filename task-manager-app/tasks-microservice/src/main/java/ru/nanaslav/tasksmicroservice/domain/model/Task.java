/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @Id
    private String id;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    /**
     * История изменений
     */
    private List<HistoryEvent> history;
    /**
     * Комментарии к задаче
     */
    private List<Comment> comments;

    /**
     * Записи о списании времени по задаче
     */
    private List<TimeRecord> timeRecords;
    /**
     * Затраченное время
     */
    private int totalTimeSpend = 0;

    /**
     * Добавление события в историю
     * @param event {@link HistoryEvent}
     */
    public void addHistoryEvent(HistoryEvent event) {
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(event);
    }

    /**
     * Добавление комментария
     * @param comment {@link Comment}
     */
    public void addComment(Comment comment) {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        comments.add(comment);
    }

    /**
     * Добавление записи о списании времени
     * @param record {@link TimeRecord}
     */
    public void addTimeRecord(TimeRecord record) {
        if (timeRecords == null) {
            timeRecords = new ArrayList<>();
        }
        timeRecords.add(record);
        totalTimeSpend += record.getTimeSpent();
    }

}
