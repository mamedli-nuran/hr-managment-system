package com.hr.system.authservice.service.impl;

import com.hr.system.authservice.dto.request.AuthenticateRequest;
import com.hr.system.authservice.dto.request.RegisterRequest;
import com.hr.system.authservice.dto.response.AuthenticateResponse;
import com.hr.system.authservice.dto.response.RegisterResponse;
import com.hr.system.authservice.security.jwt.JwtService;
import com.hr.system.authservice.mapper.UserMapper;
import com.hr.system.authservice.model.User;
import com.hr.system.authservice.repository.UserRepository;
import com.hr.system.authservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    public RegisterResponse register(RegisterRequest request) {

        // todo
        if (userRepository.existsByUsername(request.username())) {
            throw new RuntimeException();
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException();
        }

        User user = userMapper.toEntity(request);
        userRepository.save(user);

        return RegisterResponse.builder()

                .build();
    }

    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());

        String jwtToken = jwtService.generateAccessToken(userDetails);
        return userMapper.toResponse(jwtToken);
    }
}
