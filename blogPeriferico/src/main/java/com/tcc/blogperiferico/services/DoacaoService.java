package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repositories.DoacaoRepository;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Doacao> listarTodas() {
        return doacaoRepository.findAll();
    }

    public Doacao buscarPorId(Long id) {
        return doacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doação não encontrada"));
    }

    public Doacao salvar(Doacao doacao, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        doacao.setUsuario(usuario);
        return doacaoRepository.save(doacao);
    }

    public Doacao atualizar(Long id, Doacao novaDoacao, Long usuarioId) {
        Doacao existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta doação");
        }

        existente.setTitulo(novaDoacao.getTitulo());
        existente.setDescricao(novaDoacao.getDescricao());
        existente.setTelefone(novaDoacao.getTelefone());
        existente.setImagem(novaDoacao.getImagem());
        existente.setZona(novaDoacao.getZona());
        existente.setHoraPostagem(novaDoacao.getHoraPostagem());

        return doacaoRepository.save(existente);
    }

    public void excluir(Long id, Long usuarioId) {
        Doacao existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para excluir esta doação");
        }

        doacaoRepository.deleteById(id);
    }

    private boolean podeModificar(Long usuarioId, Usuario autor) {
        if (usuarioId == null || autor == null) return false;

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) return false;

        return usuario.get().getPapel() == Usuario.Papel.ADMIN ||
                usuario.get().getId().equals(autor.getId());
    }
}
