package com.hr.system.authservice.mapper;

import com.hr.system.authservice.auth.RegisterRequest;
import com.hr.system.authservice.model.RoleEnum;
import com.hr.system.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
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
}