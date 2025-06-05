package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repositories.NoticiaRepository;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Noticia> listar() {
        return noticiaRepository.findAll();
    }

    public Noticia buscarPorId(Long id) {
        return noticiaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notícia não encontrada"));
    }

    public Noticia salvar(Noticia noticia, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        noticia.setUsuario(usuario);
        return noticiaRepository.save(noticia);
    }

    public Noticia atualizar(Long id, Noticia novaNoticia, Long usuarioId) {
        Noticia existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta notícia");
        }

        existente.setTitulo(novaNoticia.getTitulo());
        existente.setTexto(novaNoticia.getTexto());
        existente.setImagem(novaNoticia.getImagem());
        existente.setZona(novaNoticia.getZona());
        existente.setHoraPostagem(novaNoticia.getHoraPostagem());

        return noticiaRepository.save(existente);
    }

    public void deletar(Long id, Long usuarioId) {
        Noticia existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para excluir esta notícia");
        }

        noticiaRepository.deleteById(id);
    }

    private boolean podeModificar(Long usuarioId, Usuario autor) {
        if (usuarioId == null || autor == null) return false;

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) return false;

        return usuario.get().getPapel() == Usuario.Papel.ADMIN ||
                usuario.get().getId().equals(autor.getId());
    }
}
