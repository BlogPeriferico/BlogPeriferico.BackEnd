package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }
}
