package com.hr.system.authservice.service;

import com.hr.system.authservice.auth.RegisterRequest;
import com.hr.system.authservice.auth.RegisterResponse;
import jakarta.validation.Valid;

public interface AuthService {
    RegisterResponse register(@Valid RegisterRequest request);
}
