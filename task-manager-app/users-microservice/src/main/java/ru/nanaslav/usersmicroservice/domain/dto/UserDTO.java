/**
 * Created on 17/05/2024
 */
package ru.nanaslav.usersmicroservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nanaslav.usersmicroservice.domain.model.User;

/**
 * Запрос-ответ с информацией о пользователе
 */
@Data
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    private String username;

    private String email;
    private String bio;
    private String qualification;

    private String avatar;

    /**
     * Конструктор для создания ответа из модели пользователя
     *
     * @param user пользователь
     */
    public UserDTO(User user) {
        this.username = user.getUsername();
        this.bio = user.getBio();
        this.email = user.getEmail();
        this.qualification = user.getQualification();
        this.avatar = user.getAvatar();
    }
}
