package com.maria.user_management_api.controller;

import com.maria.user_management_api.dto.AuthResponse;
import com.maria.user_management_api.dto.LoginRequest;
import com.maria.user_management_api.dto.RegisterRequest;
import com.maria.user_management_api.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String token = authService.registrar(request.nome(), request.username(), request.email(), request.senha());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.username(), request.senha());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
