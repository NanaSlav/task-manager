/**
 * Create on 08/05/2024
 */
package ru.nanaslav.usersmicroservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Запрос на регистрацию
 *
 * @author nana
 */
@Data
public class SignUpRequest {
    // TODO позже добавить еще почту
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
