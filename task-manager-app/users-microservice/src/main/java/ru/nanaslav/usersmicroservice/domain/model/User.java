/**
 * Created on 26/04/2024
 * Updated on 17/05/2024
 */
package ru.nanaslav.usersmicroservice.domain.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Модель пользователя
 *
 * @author nana
 * @version 1.0.0
 */
@Document("users")
@Builder
@Data
public class User implements UserDetails {
    /**
     * Id пользователя
     */
    @Id
    private String id;

    /**
     * Имя пользователя - должно быть уникальным
     */
    private String username;
    /**
     * Пароль
     */
    private String password;
    /**
     * Почта - должна быть уникальной
     */
    private String email;
    /******************************************************************************/
    /**
     * Описание пользователя
     */
    private String bio;
    /**
     * Аватар пользователя
     */
    private String avatar;
    /**
     * Квалификация работника
     */
    private String qualification;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
