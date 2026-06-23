package com.maria.user_management_api.controller;

import com.maria.user_management_api.dto.AuthResponse;
import com.maria.user_management_api.dto.LoginRequest;
import com.maria.user_management_api.dto.RegisterRequest;
import com.maria.user_management_api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints de registro e login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Registrar novo usuário")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String token = authService.registrar(request.nome(), request.username(), request.email(), request.senha());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Operation(summary = "Realizar login e obter token JWT")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.username(), request.senha());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
