package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.dto.ComentarioDTO;
import com.tcc.blogperiferico.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // Criar comentário em qualquer tipo de postagem
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<ComentarioDTO> criarComentario(@RequestBody ComentarioDTO dto) {
        return ResponseEntity.ok(comentarioService.criarComentario(dto));
    }

    // Listar comentários por tipo
    @GetMapping("/noticia/{idNoticia}")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosNoticia(@PathVariable Long idNoticia) {
        return ResponseEntity.ok(comentarioService.listarComentariosNoticia(idNoticia));
    }

    @GetMapping("/venda/{idVenda}")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosVenda(@PathVariable Long idVenda) {
        return ResponseEntity.ok(comentarioService.listarComentariosVenda(idVenda));
    }

    @GetMapping("/vaga/{idVaga}")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosVaga(@PathVariable Long idVaga) {
        return ResponseEntity.ok(comentarioService.listarComentariosVaga(idVaga));
    }

    @GetMapping("/doacao/{idDoacao}")
    public ResponseEntity<List<ComentarioDTO>> listarComentariosDoacao(@PathVariable Long idDoacao) {
        return ResponseEntity.ok(comentarioService.listarComentariosDoacao(idDoacao));
    }

    // Excluir comentário (somente dono ou ADMIN)
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirComentario(@PathVariable Long id, Authentication authentication) {
        boolean excluido = comentarioService.excluirComentario(id, authentication);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.status(403).build();
    }
}