package com.tcc.blogperiferico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.blogperiferico.dto.NoticiaDTO;
import com.tcc.blogperiferico.services.NoticiaService;

@RestController
@RequestMapping("/noticias")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class NoticiaController {

    private final NoticiaService noticiasService;
    private final ObjectMapper objectMapper;

    @Autowired
    public NoticiaController(NoticiaService noticiasService, ObjectMapper objectMapper) {
        this.noticiasService = noticiasService;
        this.objectMapper = objectMapper;
    }

    // Criar notícia com imagem
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<NoticiaDTO> criarNoticia(
            @RequestParam("dto") String dtoJson, // <- RequestParam aceita text/plain
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        NoticiaDTO dto = mapper.readValue(dtoJson, NoticiaDTO.class);

        NoticiaDTO nova = noticiasService.criarNoticia(dto, file);
        return ResponseEntity.ok(nova);
    }

    // Listar todas notícias
    @GetMapping
    public ResponseEntity<List<NoticiaDTO>> listarNoticias() {
        List<NoticiaDTO> noticias = noticiasService.listarNoticias();
        return ResponseEntity.ok(noticias);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<NoticiaDTO> buscarPorId(@PathVariable Long id) {
        Optional<NoticiaDTO> noticia = noticiasService.buscarPorId(id);
        return noticia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar notícia (trocar dados + imagem)
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<NoticiaDTO> atualizarNoticia(
            @PathVariable Long id,
            @RequestParam("dto") String dtoJson, // <- aqui também
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        NoticiaDTO dto = mapper.readValue(dtoJson, NoticiaDTO.class);

        return noticiasService.atualizarNoticia(id, dto, file)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir notícia (somente ADMIN)
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirNoticia(@PathVariable Long id) {
        boolean excluido = noticiasService.excluirNoticia(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}