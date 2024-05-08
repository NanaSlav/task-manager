/**
 * Created on 08/05/2024
 */

package ru.nanaslav.usersmicroservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.nanaslav.usersmicroservice.domain.model.User;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


/**
 * Сервис для работы с jwt
 *
 * @author nana
 */
@Service
public class JwtService {
    private String jwtSigningKey = "6A576D5A7134743777217A25432A462D4A614E645267556B5870327235753878";

    /**
     * Извлечение имени пользователя из токена
     * @param token токен
     * @return {@link String} имя пользователя
     */
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param user данные пользователя
     * @return токен
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100000 * 60 * 24))
                .signWith(SignatureAlgorithm.HS256, getSigningKey()).compact();
    }

    /**
     * Проверка валидности токена
     *
     * @param token токен
     * @param user пользователь
     * @return {@link Boolean} true если токен валиден
     */
    public boolean isTokenValid(String token, User user) {
        final String userName = extractUserName(token);
        return (userName.equals(user.getUsername())) && !isTokenExpired(token);
    }


    /**
     * Извлечение данных из токена
     *
     * @param token           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }


    /**
     * Проверка токена на просроченность
     *
     * @param token токен
     * @return true, если токен просрочен
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлечение даты истечения токена
     *
     * @param token токен
     * @return дата истечения
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

