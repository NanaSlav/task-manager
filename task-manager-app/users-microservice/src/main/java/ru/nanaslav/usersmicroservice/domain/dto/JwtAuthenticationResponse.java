/**
 * Created on 08/05/2024
 */
package ru.nanaslav.usersmicroservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ответ с токеном доступа
 *
 * @author nana
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
}
