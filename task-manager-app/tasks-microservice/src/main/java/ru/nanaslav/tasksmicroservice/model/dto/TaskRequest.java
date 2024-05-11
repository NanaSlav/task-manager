/**
 * Created on 11/05/2024
 */
package ru.nanaslav.tasksmicroservice.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Запрос с задачей
 *
 * @author nana
 */
@Data
public class TaskRequest {
    @NotBlank
    String title;
}
