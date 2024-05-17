/**
 * Created on 17/05/2024
 */
package ru.nanaslav.usersmicroservice.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Запрос на смену пароля
 *
 * @author nana
 */
@Data
public class PasswordRequest {
    @NotBlank
    String oldPassword;
    @NotBlank
    String newPassword;
}
