package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.dto.VagaDTO;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Vaga;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import com.tcc.blogperiferico.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar novo anúncio
    public VagaDTO criarVaga(VagaDTO dto) {
        System.out.println("VagaDTO recebido no serviço: " + dto);
        // Buscar o usuário pelo idUsuario
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
        System.out.println("Usuário encontrado: " + usuario.getEmail());

        // Criar a entidade Vaga
        Vaga vaga = new Vaga();
        vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setImagem(dto.getImagem());
        vaga.setZona(dto.getZona());
        vaga.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
        vaga.setIDUsuario(usuario);

        // Salvar no banco
        vaga = vagaRepository.save(vaga);
        System.out.println("Vaga salva: " + vaga.getId());

        // Retornar DTO
        return new VagaDTO(vaga);
    }

    // Listar todos os anúncios
    public List<VagaDTO> listarVagas() {
        List<Vaga> vagas = vagaRepository.findAll();
        return vagas.stream()
                .map(VagaDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<VagaDTO> buscarPorId(Long id) {
        Optional<Vaga> vaga = vagaRepository.findById(id);
        return vaga.map(VagaDTO::new);
    }

    // Atualizar anúncio
    public Optional<VagaDTO> atualizarVaga(Long id, VagaDTO dto) {
        Optional<Vaga> vagaOpt = vagaRepository.findById(id);
        if (vagaOpt.isPresent()) {
            Vaga vaga = vagaOpt.get();
            // Buscar o usuário pelo idUsuario
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
            vaga.setTitulo(dto.getTitulo());
            vaga.setDescricao(dto.getDescricao());
            vaga.setImagem(dto.getImagem());
            vaga.setZona(dto.getZona());
            vaga.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
            vaga.setIDUsuario(usuario);
            vagaRepository.save(vaga);
            return Optional.of(new VagaDTO(vaga));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirVaga(Long id) {
        if (vagaRepository.existsById(id)) {
            vagaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}