package com.umg.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // IMPORTANTE: En producción, esta clave debe estar en variables de entorno
    private static final String SECRET_KEY = "autex-secret-key-cambiar-en-produccion-debe-ser-muy-larga-minimo-256-bits";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(Integer idUsuario, String correoPrincipal, String rolPrincipal) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idUsuario", idUsuario);
        claims.put("correo", correoPrincipal);
        claims.put("rol", rolPrincipal);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(correoPrincipal)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public Integer extractUserId(String token) {
        return (Integer) extractClaims(token).get("idUsuario");
    }

    public String extractRole(String token) {
        return (String) extractClaims(token).get("rol");
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (extractUsername(token).equals(username) && !isTokenExpired(token));
    }
}