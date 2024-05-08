package ru.nanaslav.usersmicroservice.domain.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

/**
 * Запрос на аутентификацию
 *
 * @author nana
 */
@Data
public class SignInRequest {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
