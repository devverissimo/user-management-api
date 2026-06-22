package com.maria.user_management_api.service;

import com.maria.user_management_api.model.Role;
import com.maria.user_management_api.model.Usuario;
import com.maria.user_management_api.security.JwtProvider;
import com.maria.user_management_api.security.UsuarioDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtProvider jwtProvider;

    public AuthService(AuthenticationManager authenticationManager,
                       UsuarioService usuarioService,
                       JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.usuarioService = usuarioService;
        this.jwtProvider = jwtProvider;
    }

    public String login(String username, String senha) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, senha)
        );

        UsuarioDetails usuarioDetails = (UsuarioDetails) auth.getPrincipal();
        return jwtProvider.gerarToken(usuarioDetails);
    }

    public String registrar(String nome, String username, String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setRole(Role.USER);

        Usuario salvo = usuarioService.salvar(usuario);

        UsuarioDetails usuarioDetails = new UsuarioDetails(salvo);
        return jwtProvider.gerarToken(usuarioDetails);
    }
}
