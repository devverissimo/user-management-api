package com.maria.user_management_api.service;

import com.maria.user_management_api.model.Role;
import com.maria.user_management_api.model.Usuario;
import com.maria.user_management_api.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarUsuario(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> atualizar(Long id, String username, String nome, String senha, String role) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setUsername(username);
            usuario.setNome(nome);
            usuario.setSenha(passwordEncoder.encode(senha));
            usuario.setRole(Role.valueOf(role));
            return usuarioRepository.save(usuario);
        });
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
