package com.tcc.blogperiferico.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.blogperiferico.dto.DoacaoDTO;
import com.tcc.blogperiferico.services.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @Autowired
    private ObjectMapper objectMapper;

    // Criar nova doação com upload de imagem
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<DoacaoDTO> criarDoacao(
            @RequestParam("dto") String dtoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        DoacaoDTO dto = objectMapper.readValue(dtoJson, DoacaoDTO.class);
        DoacaoDTO nova = doacaoService.criarDoacao(dto, file);
        return ResponseEntity.ok(nova);
    }

    // Listar todas as doações
    @GetMapping
    public ResponseEntity<List<DoacaoDTO>> listarDoacao() {
        List<DoacaoDTO> doacoes = doacaoService.listarDoacoes();
        return ResponseEntity.ok(doacoes);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<DoacaoDTO> buscarPorId(@PathVariable Long id) {
        Optional<DoacaoDTO> doacao = doacaoService.buscarPorId(id);
        return doacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar doação (dados + imagem nova)
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<DoacaoDTO> atualizarDoacao(
            @PathVariable Long id,
            @RequestParam("dto") String dtoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        DoacaoDTO dto = objectMapper.readValue(dtoJson, DoacaoDTO.class);
        Optional<DoacaoDTO> atualizado = doacaoService.atualizarDoacao(id, dto, file);
        return atualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir doação
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id) {
        boolean excluido = doacaoService.excluirDoacao(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
