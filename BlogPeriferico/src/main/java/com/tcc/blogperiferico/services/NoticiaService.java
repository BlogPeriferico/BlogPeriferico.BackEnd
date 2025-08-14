package com.tcc.blogperiferico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.NoticiaDTO;
import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repository.NoticiaRepository;
import com.tcc.blogperiferico.repository.UsuarioRepository;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; // Adicionado para buscar Usuario

    // Criar novo anúncio
    public NoticiaDTO criarNoticia(NoticiaDTO dto) {
        System.out.println("NoticiaDTO recebido no serviço: " + dto);
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
        System.out.println("Usuário encontrado: " + usuario.getEmail());
        Noticia noticia = new Noticia();
        noticia.setLocal(dto.getLocal());
        noticia.setZona(dto.getZona());
        noticia.setTitulo(dto.getTitulo());
        noticia.setTexto(dto.getTexto());
        noticia.setImagem(dto.getImagem());
        noticia.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
        noticia.setIdUsuario(usuario);
        noticia = noticiasRepository.save(noticia);
        System.out.println("Notícia salva: " + noticia.getId());
        return new NoticiaDTO(noticia);
    }
    // Listar todos os anúncios
    public List<NoticiaDTO> listarNoticias() {
        List<Noticia> noticias = noticiasRepository.findAll();
        return noticias.stream()
                .map(NoticiaDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<NoticiaDTO> buscarPorId(Long id) {
        Optional<Noticia> noticia = noticiasRepository.findById(id);
        return noticia.map(NoticiaDTO::new);
    }

    // Atualizar anúncio
    public Optional<NoticiaDTO> atualizarNoticia(Long id, NoticiaDTO dto) {
        Optional<Noticia> noticiaOpt = noticiasRepository.findById(id);
        if (noticiaOpt.isPresent()) {
            Noticia noticia = noticiaOpt.get();

            // Buscar a entidade Usuario pelo idUsuario do DTO
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

            noticia.setLocal(dto.getLocal());
            noticia.setZona(dto.getZona());
            noticia.setTitulo(dto.getTitulo());
            noticia.setTexto(dto.getTexto());
            noticia.setImagem(dto.getImagem());
            noticia.setDataHoraCriacao(dto.getDataHoraCriacao());
            noticia.setIdUsuario(usuario);

            // Salvar no banco
            noticia = noticiasRepository.save(noticia);

            return Optional.of(new NoticiaDTO(noticia));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirNoticia(Long id) {
        if (noticiasRepository.existsById(id)) {
            noticiasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}