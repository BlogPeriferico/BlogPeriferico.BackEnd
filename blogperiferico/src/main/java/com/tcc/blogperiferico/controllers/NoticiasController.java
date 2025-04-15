package com.tcc.blogperiferico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.blogperiferico.dto.NoticiasDTO;
import com.tcc.blogperiferico.services.NoticiasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/noticias")
public class NoticiasController {

    @Autowired
    private NoticiasService noticiasService;

    // Criar uma nova noticia
    @PostMapping
    public ResponseEntity<NoticiasDTO> criarNoticias(@RequestBody NoticiasDTO dto) {
    	NoticiasDTO novoNoticias = noticiasService.criarNoticias(dto);
        return ResponseEntity.ok(novoNoticias);
    }

    // Listar todas as noticias
    @GetMapping
    public ResponseEntity<List<NoticiasDTO>> listarNoticias() {
        List<NoticiasDTO> noticias = noticiasService.listarNoticias();
        return ResponseEntity.ok(noticias);
    }

    // Buscar uma noticia por ID
    @GetMapping("/{id}")
    public ResponseEntity<NoticiasDTO> buscarPorId(@PathVariable Long id) {
        Optional<NoticiasDTO> noticias = noticiasService.buscarPorId(id);
        return noticias.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @CrossOrigin(origins = " http://127.0.0.1:5500") 
    @PostMapping("/salvar")
    public ResponseEntity<NoticiasDTO> salvar(@Valid @RequestBody NoticiasDTO noticias) {
        return ResponseEntity.ok(noticiasService.criarNoticias(noticias));
    }

    // Atualizar uma noticia por ID
    @PutMapping("/{id}")
    public ResponseEntity<NoticiasDTO> atualizarNoticia(@PathVariable Long id, @RequestBody NoticiasDTO dto) {
        Optional<NoticiasDTO> noticiasAtualizado = noticiasService.atualizarNoticia(id, dto);
        return noticiasAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir uma noticia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirNoticias(@PathVariable Long id) {
        boolean excluido = noticiasService.excluirNoticias(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
