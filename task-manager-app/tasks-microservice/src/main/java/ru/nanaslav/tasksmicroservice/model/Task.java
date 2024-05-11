/**
 * Created on 26/04/2024
 */
package ru.nanaslav.tasksmicroservice.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/**
 * Модель задачи
 *
 * @author nana
 * @version 0.0.1
 */
@Document("tasks")
public class Task {
    public Task(String title) {
        this.title = title;
    }
    @Id
    private String Id;
    private String title;
    private String status;

    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public String getAuthor() {
        return author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
