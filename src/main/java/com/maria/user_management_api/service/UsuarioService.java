package com.maria.user_management_api.service;

import com.maria.user_management_api.model.Usuario;
import org.springframework.stereotype.Service;
import com.maria.user_management_api.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public String autenticar(String username, String senha){

    }
    public String registrar(String username, String nome, String senha, String role){

    }
    public Optional<Usuario> buscarUsuario(String username){

    }
    public List<Usuario> buscarTodos(){

    }
    public Optional<Usuario> buscarPorId(Long Id){

    }
    public Optional<Usuario> atualizar(Long Id,String username, String nome, String senha, String role ){

    }
    public void deletar(Long Id){

    }
}
