package com.tcc.blogperiferico.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.blogperiferico.dto.VagaDTO;
import com.tcc.blogperiferico.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private ObjectMapper objectMapper;

    // VISITANTE pode visualizar
    @GetMapping
    public ResponseEntity<List<VagaDTO>> listarVagas() {
        return ResponseEntity.ok(vagaService.listarVagas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDTO> buscarPorId(@PathVariable Long id) {
        Optional<VagaDTO> vaga = vagaService.buscarPorId(id);
        return vaga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar vaga com imagem
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<VagaDTO> criarVaga(
            @RequestParam("dto") String dtoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        VagaDTO dto = objectMapper.readValue(dtoJson, VagaDTO.class);
        return ResponseEntity.ok(vagaService.criarVaga(dto, file));
    }

    // Atualizar vaga
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<VagaDTO> atualizarVaga(
            @PathVariable Long id,
            @RequestParam("dto") String dtoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        VagaDTO dto = objectMapper.readValue(dtoJson, VagaDTO.class);
        Optional<VagaDTO> atualizada = vagaService.atualizarVaga(id, dto, file);
        return atualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir vaga
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVaga(@PathVariable Long id) {
        boolean excluido = vagaService.excluirVaga(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}