package com.hr.system.authservice.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String JWT_SECRET_KEY,
        long ACCESS_TOKEN_EXPIRATION,
        long REFRESH_TOKEN_EXPIRATION
) {

}



