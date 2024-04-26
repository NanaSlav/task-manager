/**
 * Created on 26/04/2024
 */
package ru.nanaslav.usersmicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Модель пользователя
 *
 * @author nana
 * @version 0.0.1
 */
@Document("users")
public class User {

    public User (String username) {
        this.username = username;
    }

    @Id
    private String id;

    private final String username;


    public String getUsername() {
        return username;
    }
}
