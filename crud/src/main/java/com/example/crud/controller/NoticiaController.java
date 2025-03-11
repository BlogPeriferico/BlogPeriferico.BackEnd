package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.modal.Noticia;
import com.example.crud.service.NoticiaService;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;

    // Método para postar uma nova notícia
    @PostMapping
    public Noticia postarNoticia(@RequestParam Long userId, 
                                 @RequestParam String texto, 
                                 @RequestParam String local, 
                                 @RequestParam String imagem) {
        return noticiaService.postarNoticia(userId, texto, local, imagem);
    }

    // Método para listar todas as notícias
    @GetMapping
    public List<Noticia> listarNoticias() {
        return noticiaService.listarNoticias();
    }

    // Método para buscar uma notícia por ID
    @GetMapping("/{id}")
    public ResponseEntity<Noticia> buscarNoticiaPorId(@PathVariable Long id) {
        return noticiaService.buscarNoticiaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para atualizar uma notícia
    @PutMapping("/{id}")
    public ResponseEntity<Noticia> atualizarNoticia(@PathVariable Long id, @RequestBody Noticia noticia) {
        try {
            Noticia noticiaAtualizada = noticiaService.atualizarNoticia(id, noticia);
            return ResponseEntity.ok(noticiaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar uma notícia
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNoticia(@PathVariable Long id) {
        try {
            noticiaService.deletarNoticia(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}