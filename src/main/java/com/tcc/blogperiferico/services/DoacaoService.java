package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.dto.DoacaoDTO;
import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repository.DoacaoRepository;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AzureBlobService azureBlobService;

    // Criar nova doação
    public DoacaoDTO criarDoacao(DoacaoDTO dto, MultipartFile file) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

        Doacao doacao = new Doacao();
        doacao.setTitulo(dto.getTitulo());
        doacao.setDescricao(dto.getDescricao());
        doacao.setZona(dto.getZona());
        doacao.setTelefone(dto.getTelefone());

        if (file != null && !file.isEmpty()) {
            String urlImagem = azureBlobService.uploadFile(file);
            doacao.setImagem(urlImagem);
        }

        doacao.setDataHoraCriacao(LocalDateTime.now());
        doacao.setIdUsuario(usuario);

        doacao = doacaoRepository.save(doacao);
        return new DoacaoDTO(doacao);
    }

    // Listar
    public List<DoacaoDTO> listarDoacoes() {
        return doacaoRepository.findAll().stream().map(DoacaoDTO::new).collect(Collectors.toList());
    }

    // Buscar por ID
    public Optional<DoacaoDTO> buscarPorId(Long id) {
        return doacaoRepository.findById(id).map(DoacaoDTO::new);
    }

    // Atualizar doação
    public Optional<DoacaoDTO> atualizarDoacao(Long id, DoacaoDTO dto, MultipartFile file) {
        Optional<Doacao> doacaoOpt = doacaoRepository.findById(id);
        if (doacaoOpt.isPresent()) {
            Doacao doacao = doacaoOpt.get();

            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

            doacao.setTitulo(dto.getTitulo());
            doacao.setDescricao(dto.getDescricao());
            doacao.setZona(dto.getZona());
            doacao.setTelefone(dto.getTelefone());

            if (file != null && !file.isEmpty()) {
                String urlImagem = azureBlobService.uploadFile(file);
                doacao.setImagem(urlImagem);
            }

            doacao.setDataHoraCriacao(LocalDateTime.now());
            doacao.setIdUsuario(usuario);

            doacao = doacaoRepository.save(doacao);
            return Optional.of(new DoacaoDTO(doacao));
        }
        return Optional.empty();
    }

    // Excluir
    public boolean excluirDoacao(Long id) {
        if (doacaoRepository.existsById(id)) {
            doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
