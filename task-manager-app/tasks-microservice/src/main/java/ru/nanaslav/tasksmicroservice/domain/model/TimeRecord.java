/**
 * Created on 20/05/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Запись о списании времени по задаче
 *
 * @author nana
 */
@Data
@AllArgsConstructor
public class TimeRecord {
    /**
     * Исполнитель задачи
     */
    private String executor;

    /**
     * Затраченное время (в минутах)
     */
    private int timeSpent;
    /**
     * Комментарий о списании времени
     */
    private String comment;
}
