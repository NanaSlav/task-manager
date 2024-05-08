/**
 * Created on 26/04/2024
 */
package ru.nanaslav.usersmicroservice.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Модель пользователя
 *
 * @author nana
 * @version 0.0.1
 */
@Document("users")
@Data
@Builder
public class User implements UserDetails {

    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    private String id;

    private final String username;

    private String password;

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
