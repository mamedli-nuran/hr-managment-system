package com.hr.system.authservice.service;

import com.hr.system.authservice.auth.RegisterRequest;
import com.hr.system.authservice.auth.RegisterResponse;
import com.hr.system.authservice.mapper.UserMapper;
import com.hr.system.authservice.model.User;
import com.hr.system.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.username())){
            throw new RuntimeException();
        }
        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException();
        }

        User user = userMapper.toEntity(request);
        userRepository.save(user);

        return RegisterResponse.builder()

                .build();
    }
}
