package com.hr.system.authservice.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;


@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtProperties jwtProperties;
    private SecretKey signKey;

    public String generateAccessToken(
        Map<String, Object> claims,
        UserDetails userDetails
    ){
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.ACCESS_TOKEN_EXPIRATION()))
                .signWith(signKey)
                .compact();
    }

    public String generateAccessToken(
            UserDetails userDetails) {
        return generateAccessToken(new HashMap<>(), userDetails);
    }


    public String generateRefreshToken(
            UserDetails userDetails
    ){
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.REFRESH_TOKEN_EXPIRATION()))
                .signWith(signKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        return extractAllClaims(token).get("roles", List.class);
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    @PostConstruct
    public void init() {
        byte [] keyBytes = Base64.getDecoder().decode(jwtProperties.JWT_SECRET_KEY());
        this.signKey = Keys.hmacShaKeyFor(keyBytes);
    }
}
