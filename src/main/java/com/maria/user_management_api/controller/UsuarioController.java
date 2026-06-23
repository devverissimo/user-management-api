package com.maria.user_management_api.controller;

import com.maria.user_management_api.dto.AtualizarUsuarioRequest;
import com.maria.user_management_api.dto.UsuarioResponse;
import com.maria.user_management_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "CRUD de usuários (requer autenticação)")
@SecurityRequirement(name = "Bearer Authentication")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Listar todos os usuários")
    @GetMapping
    public List<UsuarioResponse> listar() {
        return usuarioService.buscarTodos().stream()
                .map(UsuarioResponse::de)
                .toList();
    }

    @Operation(summary = "Buscar usuário por ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(UsuarioResponse::de)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualizar usuário")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long id,
                                                     @RequestBody AtualizarUsuarioRequest request) {
        return usuarioService.atualizar(id, request.username(), request.nome(), request.senha(), request.role())
                .map(UsuarioResponse::de)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar usuário")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
