package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Vaga;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import com.tcc.blogperiferico.repositories.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Vaga> listarTodas() {
        return vagaRepository.findAll();
    }

    public Vaga buscarPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));
    }

    public Vaga salvar(Vaga vaga, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        vaga.setUsuario(usuario);
        return vagaRepository.save(vaga);
    }

    public Vaga atualizar(Long id, Vaga novaVaga, Long usuarioId) {
        Vaga existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta vaga");
        }

        existente.setTitulo(novaVaga.getTitulo());
        existente.setDescricao(novaVaga.getDescricao());
        existente.setZona(novaVaga.getZona());
        existente.setImagem(novaVaga.getImagem());
        existente.setHoraPostagem(novaVaga.getHoraPostagem());

        return vagaRepository.save(existente);
    }

    public void excluir(Long id, Long usuarioId) {
        Vaga vaga = buscarPorId(id);

        if (!podeModificar(usuarioId, vaga.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para excluir esta vaga");
        }

        vagaRepository.deleteById(id);
    }

    private boolean podeModificar(Long usuarioId, Usuario autor) {
        if (usuarioId == null || autor == null) return false;

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) return false;

        return usuario.get().getPapel() == Usuario.Papel.ADMIN ||
                usuario.get().getId().equals(autor.getId());
    }
}
