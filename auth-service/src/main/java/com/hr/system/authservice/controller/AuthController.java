package com.hr.system.authservice.controller;

import com.hr.system.authservice.dto.request.AuthenticateRequest;
import com.hr.system.authservice.dto.request.RegisterRequest;
import com.hr.system.authservice.dto.response.RegisterResponse;
import com.hr.system.authservice.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }


    @PostMapping("/authonticate")
    public ResponseEntity<?> login (@RequestBody AuthenticateRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authService.authenticate(request));
    }



}
