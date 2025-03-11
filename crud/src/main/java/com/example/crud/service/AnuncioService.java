package com.example.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.modal.Anuncio;
import com.example.crud.modal.Usuario;
import com.example.crud.repository.AnuncioRepository;
import com.example.crud.repository.UsuarioRepository;

@Service
public class AnuncioService {
    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;   

    // Método para criar um novo anúncio
    public Anuncio criarAnuncio(Long userId, String texto, String local, String contato, Double preco, String imagemProduto) {
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Anuncio anuncio = new Anuncio();
        anuncio.setTexto(texto);
        anuncio.setLocal(local);
        anuncio.setContato(contato);
        anuncio.setPreco(preco);
        anuncio.setImagemProduto(imagemProduto);  
        anuncio.setDataPublicacao(LocalDateTime.now());
        anuncio.setUsuario(usuario);

        return anuncioRepository.save(anuncio);
    }

     
    public List<Anuncio> listarAnuncios() {
        return anuncioRepository.findAll();
    }

     
    public Optional<Anuncio> buscarAnuncioPorId(Long id) {
        return anuncioRepository.findById(id);
    }

    
    public Anuncio atualizarAnuncio(Long id, Anuncio anuncioAtualizado) {
        Anuncio anuncioExistente = anuncioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        anuncioExistente.setTexto(anuncioAtualizado.getTexto());
        anuncioExistente.setLocal(anuncioAtualizado.getLocal());
        anuncioExistente.setContato(anuncioAtualizado.getContato());
        anuncioExistente.setPreco(anuncioAtualizado.getPreco());
        anuncioExistente.setImagemProduto(anuncioAtualizado.getImagemProduto());
        

        return anuncioRepository.save(anuncioExistente);
    }

    
    public void deletarAnuncio(Long id) {
        if (!anuncioRepository.existsById(id)) {
            throw new RuntimeException("Anúncio não encontrado");
        }
        anuncioRepository.deleteById(id);
    }
}