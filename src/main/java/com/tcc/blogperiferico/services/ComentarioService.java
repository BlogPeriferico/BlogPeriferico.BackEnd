package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.dto.ComentarioDTO;
import com.tcc.blogperiferico.entities.*;
import com.tcc.blogperiferico.enums.UsuarioRole;
import com.tcc.blogperiferico.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VagaRepository vagaRepository;

    @Autowired
    private DoacaoRepository doacaoRepository;

    // Criar comentário
    public ComentarioDTO criarComentario(ComentarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        Comentario comentario = new Comentario();
        comentario.setTexto(dto.getTexto());
        comentario.setDataHoraCriacao(LocalDateTime.now());
        comentario.setUsuario(usuario);

        if (dto.getIdNoticia() != null) {
            Noticia noticia = noticiaRepository.findById(dto.getIdNoticia())
                    .orElseThrow(() -> new IllegalArgumentException("Notícia não encontrada"));
            comentario.setNoticia(noticia);
        } else if (dto.getIdVenda() != null) {
            Venda venda = vendaRepository.findById(dto.getIdVenda())
                    .orElseThrow(() -> new IllegalArgumentException("Venda não encontrada"));
            comentario.setVenda(venda);
        } else if (dto.getIdVaga() != null) {
            Vaga vaga = vagaRepository.findById(dto.getIdVaga())
                    .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));
            comentario.setVaga(vaga);
        } else if (dto.getIdDoacao() != null) {
            Doacao doacao = doacaoRepository.findById(dto.getIdDoacao())
                    .orElseThrow(() -> new IllegalArgumentException("Doação não encontrada"));
            comentario.setDoacao(doacao);
        } else {
            throw new IllegalArgumentException("É necessário informar o tipo da postagem (Notícia, Venda, Vaga ou Doação)");
        }

        comentario = comentarioRepository.save(comentario);
        return new ComentarioDTO(comentario);
    }

    // Listar comentários por tipo
    public List<ComentarioDTO> listarComentariosNoticia(Long idNoticia) {
        return comentarioRepository.findByNoticiaId(idNoticia).stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> listarComentariosVenda(Long idVenda) {
        return comentarioRepository.findByVendaId(idVenda).stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> listarComentariosVaga(Long idVaga) {
        return comentarioRepository.findByVagaId(idVaga).stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    public List<ComentarioDTO> listarComentariosDoacao(Long idDoacao) {
        return comentarioRepository.findByDoacaoId(idDoacao).stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    // Excluir comentário (dono ou ADMIN)
    public boolean excluirComentario(Long id, Authentication authentication) {
        Comentario comentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comentário não encontrado"));

        Usuario usuarioLogado = usuarioRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("Usuário logado não encontrado"));

        if (comentario.getUsuario().getId().equals(usuarioLogado.getId()) ||
                usuarioLogado.getRoles() == UsuarioRole.ROLE_ADMINISTRADOR) {
            comentarioRepository.delete(comentario);
            return true;
        }
        return false;
    }
}