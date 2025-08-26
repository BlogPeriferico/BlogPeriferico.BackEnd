package com.tcc.blogperiferico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tcc.blogperiferico.dto.UsuarioDTO;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.enums.UsuarioRole;
import com.tcc.blogperiferico.exception.SemResultadosException;
import com.tcc.blogperiferico.exception.UsuarioDuplicadoException;
import com.tcc.blogperiferico.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AzureBlobService azureBlobService; // ✅ usado para upload

    public UsuarioDTO listar(Long id) {
        return new UsuarioDTO(repository.findById(id)
                .orElseThrow(() -> new SemResultadosException()));
    }

    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO salvar(UsuarioDTO user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new UsuarioDuplicadoException();
        }

        Usuario usuario = new Usuario();
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(encoder.encode(user.getSenha()));
        usuario.setRoles(UsuarioRole.ROLE_USUARIO);
        usuario.setFotoPerfil(user.getFotoPerfil());

        return new UsuarioDTO(repository.save(usuario));
    }

    public UsuarioDTO atualizartudo(UsuarioDTO user, Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new SemResultadosException("atualização."));

        if (repository.existsByEmail(user.getEmail(), id)) {
            throw new UsuarioDuplicadoException();
        }

        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setSenha(encoder.encode(user.getSenha()));
        usuario.setRoles(user.getRoles());
        usuario.setFotoPerfil(user.getFotoPerfil());

        return new UsuarioDTO(repository.save(usuario));
    }

    public UsuarioDTO atualizar(UsuarioDTO user, Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new SemResultadosException("atualização."));

        if (!user.getNome().equals(usuario.getNome())) usuario.setNome(user.getNome());
        if (!user.getEmail().equals(usuario.getEmail())) usuario.setEmail(user.getEmail());
        if (!user.getSenha().equals(usuario.getSenha())) usuario.setSenha(encoder.encode(user.getSenha()));
        if (!user.getRoles().equals(usuario.getRoles())) usuario.setRoles(user.getRoles());
        if (user.getFotoPerfil() != null) usuario.setFotoPerfil(user.getFotoPerfil());

        return new UsuarioDTO(repository.save(usuario));
    }

    // Atualizar só a foto do perfil
    public UsuarioDTO atualizarFoto(Long id, MultipartFile file) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new SemResultadosException("usuário não encontrado."));

        if (file != null && !file.isEmpty()) {
            String url = azureBlobService.uploadFile(file);
            usuario.setFotoPerfil(url);
        }

        return new UsuarioDTO(repository.save(usuario));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new SemResultadosException();
        }
        repository.deleteById(id);
    }
}