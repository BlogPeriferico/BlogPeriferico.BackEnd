package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.modal.Usuario;
import com.example.crud.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	  @Autowired
	    private UsuarioRepository usuarioRepository;

	    public List<Usuario> listarUsuarios() {
	        return usuarioRepository.findAll();
	    }

	    public Optional<Usuario> obterUsuario(Long id) {
	        return usuarioRepository.findById(id);
	    }

	    public Usuario salvarUsuario(Usuario usuario) {
	        return usuarioRepository.save(usuario);
	    }

	    public void deletarUsuario(Long id) {
	        usuarioRepository.deleteById(id);
	    }
	    
}
