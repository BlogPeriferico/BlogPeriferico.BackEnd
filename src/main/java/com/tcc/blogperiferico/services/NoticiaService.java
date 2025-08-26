package com.tcc.blogperiferico.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tcc.blogperiferico.dto.NoticiaDTO;
import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.repository.NoticiaRepository;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AzureBlobService azureBlobService;

    public NoticiaDTO criarNoticia(NoticiaDTO dto, MultipartFile file) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

        Noticia noticia = new Noticia();
        noticia.setLocal(dto.getLocal());
        noticia.setZona(dto.getZona());
        noticia.setTitulo(dto.getTitulo());
        noticia.setTexto(dto.getTexto());

        if (file != null && !file.isEmpty()) {
            String urlImagem = azureBlobService.uploadFile(file);
            noticia.setImagem(urlImagem);
        }

        noticia.setDataHoraCriacao(LocalDateTime.now());
        noticia.setIdUsuario(usuario);

        noticia = noticiasRepository.save(noticia);
        return new NoticiaDTO(noticia);
    }

    public List<NoticiaDTO> listarNoticias() {
        List<Noticia> noticias = noticiasRepository.findAll();
        return noticias.stream()
                .map(NoticiaDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<NoticiaDTO> buscarPorId(Long id) {
        Optional<Noticia> noticia = noticiasRepository.findById(id);
        return noticia.map(NoticiaDTO::new);
    }

    public Optional<NoticiaDTO> atualizarNoticia(Long id, NoticiaDTO dto, MultipartFile file) {
        Optional<Noticia> noticiaOpt = noticiasRepository.findById(id);

        if (noticiaOpt.isPresent()) {
            Noticia noticia = noticiaOpt.get();

            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));

            noticia.setLocal(dto.getLocal());
            noticia.setZona(dto.getZona());
            noticia.setTitulo(dto.getTitulo());
            noticia.setTexto(dto.getTexto());

            if (file != null && !file.isEmpty()) {
                String urlImagem = azureBlobService.uploadFile(file);
                noticia.setImagem(urlImagem);
            }

            noticia.setDataHoraCriacao(LocalDateTime.now());
            noticia.setIdUsuario(usuario);

            noticia = noticiasRepository.save(noticia);
            return Optional.of(new NoticiaDTO(noticia));
        }
        return Optional.empty();
    }

    public boolean excluirNoticia(Long id) {
        if (noticiasRepository.existsById(id)) {
            noticiasRepository.deleteById(id);
            return true;
        }
        return false;
    }
}