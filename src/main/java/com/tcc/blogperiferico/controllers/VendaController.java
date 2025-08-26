package com.tcc.blogperiferico.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcc.blogperiferico.dto.VendaDTO;
import com.tcc.blogperiferico.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private ObjectMapper objectMapper;

    // VISITANTE pode visualizar
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        return ResponseEntity.ok(vendaService.listarVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable Long id) {
        Optional<VendaDTO> venda = vendaService.buscarPorId(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Criar venda com imagem
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<VendaDTO> criarVenda(
            @RequestParam("dto") String dtoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        VendaDTO dto = objectMapper.readValue(dtoJson, VendaDTO.class);
        return ResponseEntity.ok(vendaService.criarVenda(dto, file));
    }

    // Atualizar venda
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<VendaDTO> atualizarVenda(
            @PathVariable Long id,
            @RequestParam("dto") String dtoJson,
            @RequestPart(value = "file", required = false) MultipartFile file) throws Exception {

        VendaDTO dto = objectMapper.readValue(dtoJson, VendaDTO.class);
        Optional<VendaDTO> atualizada = vendaService.atualizarVenda(id, dto, file);
        return atualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir venda
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
        boolean excluido = vendaService.excluirVenda(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}