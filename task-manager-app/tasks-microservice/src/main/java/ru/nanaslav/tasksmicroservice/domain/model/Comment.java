/**
 * Created on 16/05/2024
 */
package ru.nanaslav.tasksmicroservice.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * Комментарий к задаче
 *
 * @author nana
 */
@Data
public class Comment {

    /**
     * Текст комментария
     */
    private String text;
    /**
     * Автор комментария
     */
    private String author;
    /**
     * Время написания комментария
     */
    private Date creationTime;
}
