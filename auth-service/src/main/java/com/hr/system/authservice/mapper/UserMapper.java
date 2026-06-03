package com.hr.system.authservice.mapper;

import com.hr.system.authservice.dto.request.RegisterRequest;
import com.hr.system.authservice.dto.response.AuthenticateResponse;
import com.hr.system.authservice.dto.response.RegisterResponse;
import com.hr.system.authservice.model.RoleEnum;
import com.hr.system.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toEntity(RegisterRequest registerRequest) {
        return User.builder()
                .role(RoleEnum.ROLE_USER)
                .username(registerRequest.username())
                .password(passwordEncoder.encode(registerRequest.password()))
                .email(registerRequest.email())
                .build();
    }

    public AuthenticateResponse toResponse(String jwtToken) {
        return AuthenticateResponse.builder()
                .jwt(jwtToken)
                .build();
    }
}