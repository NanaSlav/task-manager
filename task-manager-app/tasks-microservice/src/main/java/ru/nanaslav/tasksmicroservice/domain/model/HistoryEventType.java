/**
 * Created on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import lombok.Getter;

/**
 * Тип события в истории
 */
@Getter
public enum HistoryEventType {
    CREATION("Создана задача"),
    STATUS_CHANGED("Изменен статус"),
    COMMENT_ADDED("Добавлен комментарий"),
    PRIORITY_CHANGED("Изменен приоритет"),
    ASSIGNEE_CHANGED("Изменен исполнитель"),
    DEADLINE_CHANGED("Изменен срок выполения"),
    DESCRIPTION_CHANGED("Изменено описание задачи"),
    DIFFICULTY_CHANGED("Изменена оценка сложности");


    private final String description;

    HistoryEventType(String description) {
        this.description = description;
    }
}
