package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.repositories.UsuarioRepository;
import com.tcc.blogperiferico.repositories.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda não encontrada"));
    }

    public Venda salvar(Venda venda, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        venda.setUsuario(usuario);
        return vendaRepository.save(venda);
    }

    public Venda atualizar(Long id, Venda novaVenda, Long usuarioId) {
        Venda existente = buscarPorId(id);

        if (!podeModificar(usuarioId, existente.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para editar esta venda");
        }

        existente.setTitulo(novaVenda.getTitulo());
        existente.setDescricao(novaVenda.getDescricao());
        existente.setImagem(novaVenda.getImagem());
        existente.setTelefone(novaVenda.getTelefone());
        existente.setValor(novaVenda.getValor());
        existente.setCpf(novaVenda.getCpf());
        existente.setZona(novaVenda.getZona());
        existente.setHoraPostagem(novaVenda.getHoraPostagem());

        return vendaRepository.save(existente);
    }

    public void excluir(Long id, Long usuarioId) {
        Venda venda = buscarPorId(id);

        if (!podeModificar(usuarioId, venda.getUsuario())) {
            throw new AccessDeniedException("Você não tem permissão para excluir esta venda");
        }

        vendaRepository.deleteById(id);
    }

    private boolean podeModificar(Long usuarioId, Usuario autor) {
        if (usuarioId == null || autor == null) return false;

        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isEmpty()) return false;

        return usuario.get().getPapel() == Usuario.Papel.ADMIN ||
                usuario.get().getId().equals(autor.getId());
    }
}
