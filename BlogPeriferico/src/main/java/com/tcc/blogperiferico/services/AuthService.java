package com.tcc.blogperiferico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.enums.UsuarioRole;
import com.tcc.blogperiferico.repository.UsuarioRepository;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isOwnerOrAdmin(Long idUsuarioCriador, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByEmail(authentication.getName()).orElse(null);
        if (usuario == null) return false;

        return usuario.getRoles() == UsuarioRole.ROLE_ADMINISTRADOR || usuario.getId().equals(idUsuarioCriador);
    }
}
