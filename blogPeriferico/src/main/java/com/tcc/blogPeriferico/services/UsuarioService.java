package com.tcc.blogPeriferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogPeriferico.dto.UsuarioDTO;
import com.tcc.blogPeriferico.entities.Usuario;
import com.tcc.blogPeriferico.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
    private UsuarioRepository usuarioRepository;

    // Criar novo anúncio
    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
    	Usuario usuario = new Usuario(dto.getId(), dto.getNome(), dto.getEmail(), dto.getSenha(), dto.getCpf() ,dto.getRoles());
    	usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getRoles());
    }

    // Listar todos os anúncios
    public List<UsuarioDTO> listarUsuario() {
        List<Usuario> usuario = usuarioRepository.findAll();
        return usuario.stream()
                .map(a -> new UsuarioDTO(a.getId(), a.getNome(), a.getEmail(), a.getSenha(), a.getCpf(), a.getRoles()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<UsuarioDTO> buscarPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(a -> new UsuarioDTO(a.getId(), a.getNome(), a.getEmail(), a.getSenha(), a.getCpf(), a.getRoles()));
    }

    // Atualizar anúncio
    public Optional<UsuarioDTO> atualizarUsuario(Long id, UsuarioDTO dto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
        	Usuario usuario = usuarioOpt.get();
        	usuario.setNome(dto.getNome());
        	usuario.setEmail(dto.getEmail());
        	usuario.setSenha(dto.getSenha());
        	usuario.setCpf(dto.getCpf());
        	usuario.setRoles(dto.getRoles());
        	usuarioRepository.save(usuario);
            return Optional.of(new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCpf(), usuario.getRoles()));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
        	usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }	
}
