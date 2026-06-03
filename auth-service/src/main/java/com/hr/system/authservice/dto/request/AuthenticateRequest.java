package com.hr.system.authservice.dto.request;

public record AuthenticateRequest(
        String username,
        String password
) {
}
