package com.example.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.modal.Noticia;
import com.example.crud.modal.Usuario;
import com.example.crud.repository.NoticiaRepository;
import com.example.crud.repository.UsuarioRepository;

@Service
public class NoticiaService {

    @Autowired
    private NoticiaRepository noticiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    public Noticia postarNoticia(Long userId, String texto, String local, String imagem) {
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Noticia noticia = new Noticia();
        noticia.setTexto(texto);
        noticia.setLocal(local);
        noticia.setImagem(imagem); 
        noticia.setDataPublicacao(LocalDateTime.now());
        noticia.setUsuario(usuario);

        return noticiaRepository.save(noticia);
    }

    public List<Noticia> listarNoticias() {
        return noticiaRepository.findAll();
    }

    public Optional<Noticia> buscarNoticiaPorId(Long id) {
        return noticiaRepository.findById(id);
    }

    public Noticia atualizarNoticia(Long id, Noticia noticiaAtualizada) {
        Noticia noticiaExistente = noticiaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Notícia não encontrada"));

        noticiaExistente.setTexto(noticiaAtualizada.getTexto());
        noticiaExistente.setLocal(noticiaAtualizada.getLocal());
        noticiaExistente.setImagem(noticiaAtualizada.getImagem());

        return noticiaRepository.save(noticiaExistente);
    }

    public void deletarNoticia(Long id) {
        if (!noticiaRepository.existsById(id)) {
            throw new RuntimeException("Notícia não encontrada");
        }
        noticiaRepository.deleteById(id);
    }
}