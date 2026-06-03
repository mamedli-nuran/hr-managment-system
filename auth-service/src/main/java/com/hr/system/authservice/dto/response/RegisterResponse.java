package com.hr.system.authservice.dto.response;

import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record RegisterResponse(
        UUID id,
        String username,
        String email,
        Set<String> roles,
        String message
) {
}