package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.modal.Anuncio;
import com.example.crud.service.AnuncioService;

import java.util.List;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {
    @Autowired
    private AnuncioService anuncioService;

    // Método para criar um novo anúncio
    @PostMapping("/post")
    public Anuncio criarAnuncio(@RequestParam Long userId, 
                                @RequestParam String texto, 
                                @RequestParam String local, 
                                @RequestParam String contato, 
                                @RequestParam Double preco, 
                                @RequestParam String imagemProduto) {
        return anuncioService.criarAnuncio(userId, texto, local, contato, preco, imagemProduto);
    }

    // Método para listar todos os anúncios
    @GetMapping
    public List<Anuncio> listarAnuncios() {
        return anuncioService.listarAnuncios();
    }

    // Método para buscar um anúncio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Anuncio> buscarAnuncioPorId(@PathVariable Long id) {
        return anuncioService.buscarAnuncioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para atualizar um anúncio
    @PutMapping("/{id}")
    public ResponseEntity<Anuncio> atualizarAnuncio(@PathVariable Long id, @RequestBody Anuncio anuncio) {
        try {
            Anuncio anuncioAtualizado = anuncioService.atualizarAnuncio(id, anuncio);
            return ResponseEntity.ok(anuncioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um anúncio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAnuncio(@PathVariable Long id) {
        try {
            anuncioService.deletarAnuncio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}