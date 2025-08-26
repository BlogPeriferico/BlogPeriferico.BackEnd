package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.dto.VagaDTO;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Vaga;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import com.tcc.blogperiferico.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private AzureBlobService azureBlobService;

    // Criar nova vaga
    public VagaDTO criarVaga(VagaDTO dto, MultipartFile file) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

        Vaga vaga = new Vaga();
        vaga.setTitulo(dto.getTitulo());
        vaga.setDescricao(dto.getDescricao());
        vaga.setZona(dto.getZona());

        if (file != null && !file.isEmpty()) {
            String urlImagem = azureBlobService.uploadFile(file);
            vaga.setImagem(urlImagem);
        }

        vaga.setDataHoraCriacao(LocalDateTime.now());
        vaga.setIDUsuario(usuario);

        vaga = vagaRepository.save(vaga);
        return new VagaDTO(vaga);
    }

    // Listar
    public List<VagaDTO> listarVagas() {
        return vagaRepository.findAll().stream()
                .map(VagaDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<VagaDTO> buscarPorId(Long id) {
        return vagaRepository.findById(id).map(VagaDTO::new);
    }

    // Atualizar vaga
    public Optional<VagaDTO> atualizarVaga(Long id, VagaDTO dto, MultipartFile file) {
        Optional<Vaga> vagaOpt = vagaRepository.findById(id);
        if (vagaOpt.isPresent()) {
            Vaga vaga = vagaOpt.get();

            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

            vaga.setTitulo(dto.getTitulo());
            vaga.setDescricao(dto.getDescricao());
            vaga.setZona(dto.getZona());

            if (file != null && !file.isEmpty()) {
                String urlImagem = azureBlobService.uploadFile(file);
                vaga.setImagem(urlImagem);
            }

            vaga.setDataHoraCriacao(LocalDateTime.now());
            vaga.setIDUsuario(usuario);

            vaga = vagaRepository.save(vaga);
            return Optional.of(new VagaDTO(vaga));
        }
        return Optional.empty();
    }

    // Excluir vaga
    public boolean excluirVaga(Long id) {
        if (vagaRepository.existsById(id)) {
            vagaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}