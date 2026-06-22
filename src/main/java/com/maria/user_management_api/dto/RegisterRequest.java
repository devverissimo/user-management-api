package com.maria.user_management_api.dto;

public record RegisterRequest(String nome, String username, String email, String senha) {
}
