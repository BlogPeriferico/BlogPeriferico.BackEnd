package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.entities.Comentario;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repositories.ComentarioRepository;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Comentario> listarTodos() {
        return comentarioRepository.findAll();
    }

    public Comentario buscarPorId(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentário não encontrado"));
    }

    public Comentario salvar(Comentario comentario, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        comentario.setUsuario(usuario);
        return comentarioRepository.save(comentario);
    }

    public Comentario atualizar(Long id, Comentario novoComentario, Long usuarioId) {
        Comentario existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para editar este comentário");
        }

        existente.setTexto(novoComentario.getTexto());
        existente.setCategoria(novoComentario.getCategoria());
        existente.setHoraPostagem(novoComentario.getHoraPostagem());

        return comentarioRepository.save(existente);
    }

    public void excluir(Long id, Long usuarioId) {
        Comentario existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para excluir este comentário");
        }

        comentarioRepository.deleteById(id);
    }

    private boolean podeModificar(Long usuarioId, Usuario autorDoComentario) {
        if (usuarioId == null || autorDoComentario == null) return false;

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        if (usuarioOptional.isEmpty()) return false;

        Usuario usuario = usuarioOptional.get();

        return usuario.getPapel() == Usuario.Papel.ADMIN ||
                usuario.getId().equals(autorDoComentario.getId());
    }
}
