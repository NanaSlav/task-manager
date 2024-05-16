/**
 * Created on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * Событие в истории
 *
 * @author nana
 */
@Data
public class HistoryEvent {
    /**
     * Тип события
     */
    private HistoryEventType historyEventType;
    /**
     * Автор события
     */
    private String author;
    /**
     * Время события
     */
    private Date eventTime;

    /**
     * Описание события
     */
    private String description;
}
