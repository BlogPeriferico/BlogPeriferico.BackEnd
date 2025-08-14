package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.dto.DoacaoDTO;
import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repository.DoacaoRepository;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Adicionado

    // Criar nova Doacao
    public DoacaoDTO criarDoacao(DoacaoDTO dto) {
        System.out.println("DoacaoDTO recebido no serviço: " + dto);
        // Buscar o usuário pelo idUsuario
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
        System.out.println("Usuário encontrado: " + usuario.getEmail());

        // Criar a entidade Doacao
        Doacao doacao = new Doacao();
        doacao.setTitulo(dto.getTitulo());
        doacao.setDescricao(dto.getDescricao());
        doacao.setImagem(dto.getImagem());
        doacao.setZona(dto.getZona());
        doacao.setTelefone(dto.getTelefone());
        doacao.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
        doacao.setIdUsuario(usuario);

        // Salvar no banco
        doacao = doacaoRepository.save(doacao);
        System.out.println("Doação salva: " + doacao.getId());

        // Retornar DTO
        return new DoacaoDTO(doacao);
    }

    // Listar todos os anúncios
    public List<DoacaoDTO> listarDoacoes() {
        List<Doacao> doacoes = doacaoRepository.findAll();
        return doacoes.stream()
                .map(DoacaoDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<DoacaoDTO> buscarPorId(Long id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);
        return doacao.map(DoacaoDTO::new);
    }

    // Atualizar anúncio
    public Optional<DoacaoDTO> atualizarDoacao(Long id, DoacaoDTO dto) {
        Optional<Doacao> doacaoOpt = doacaoRepository.findById(id);
        if (doacaoOpt.isPresent()) {
            Doacao doacao = doacaoOpt.get();
            // Buscar o usuário pelo idUsuario
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
            doacao.setTitulo(dto.getTitulo());
            doacao.setDescricao(dto.getDescricao());
            doacao.setImagem(dto.getImagem());
            doacao.setZona(dto.getZona());
            doacao.setTelefone(dto.getTelefone());
            doacao.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
            doacao.setIdUsuario(usuario);
            doacaoRepository.save(doacao);
            return Optional.of(new DoacaoDTO(doacao));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirDoacao(Long id) {
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}