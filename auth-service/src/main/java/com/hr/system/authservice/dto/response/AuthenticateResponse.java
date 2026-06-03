package com.hr.system.authservice.dto.response;

import lombok.Builder;

@Builder
public record AuthenticateResponse(
        String jwt
) {
}
