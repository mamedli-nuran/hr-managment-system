package com.hr.system.authservice.service;

import com.hr.system.authservice.dto.request.AuthenticateRequest;
import com.hr.system.authservice.dto.request.RegisterRequest;
import com.hr.system.authservice.dto.response.AuthenticateResponse;
import com.hr.system.authservice.dto.response.RegisterResponse;
import jakarta.validation.Valid;

public interface AuthService {
    RegisterResponse register(@Valid RegisterRequest request);
    AuthenticateResponse authenticate (AuthenticateRequest request);
}
