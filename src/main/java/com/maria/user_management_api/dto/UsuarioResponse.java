package com.maria.user_management_api.dto;

import com.maria.user_management_api.model.Usuario;

public record UsuarioResponse(Long id, String nome, String username, String email, String role) {

    public static UsuarioResponse de(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole() != null ? usuario.getRole().name() : null
        );
    }
}
