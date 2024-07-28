package com.mballem.demo_park_api.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class JwtUtils {
    ;
    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    public static final String SECRET_KEY = System.getenv("SECRET_KEY_JWT");
    public static final long EXPIRE_DAYS = 0;
    public static final long EXPIRE_HOURS = 0;
    public static final long EXPIRE_MINUTES = 30;

    private JwtUtils() {
    }

    // prepara a chave para que ela possa ser criptografada
    private static SecretKey generateKey() {
        log.info("Entrei na função generateKey 2°(POST -> /api/auth)");
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // cria data de expiração do token
    private static Date toExpireDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    // gera token JWT
    public static JwtToken createToken(String username, String role) {
        log.info("Entrei na função createToken 1°(POST -> /api/auth)");
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt);
        String token = Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(limit)
                .signWith(generateKey())
                .claim("role", role)
                .compact();
        return new JwtToken(token);
    }

    // recupera o corpo do token, remvendo a instruçãõ BEARER
    private static String refactorToken(String token) {
        log.info("Token recebido: {}", token);
        log.info("Entrei na função refactorToken (GET -> /api/auth)");
        if (token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }

        return token;
    }

    // recupera o conteúdo do token
    private static Claims getClaimsFromToken(String token) {
        try {
            log.info("Token recebido: {}", token);
            log.info("Entrei na função getClaimsFromToken (GET -> /api/auth)");
            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactorToken(token)).getPayload();
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return null;
    }

    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token) {
        try {
            log.info("Token recebido: {}", token);
            log.info("Entrei na função isTokenValid (GET -> /api/auth)");
            Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(refactorToken(token));
            return true;
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return false;
    }


}
