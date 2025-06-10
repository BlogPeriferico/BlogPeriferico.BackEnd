package com.tcc.blogperiferico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.blogperiferico.dto.VendaDTO;
import com.tcc.blogperiferico.services.VendaService;

@RestController
@RequestMapping("/anuncios")
public class VendaController {

    @Autowired
    private VendaService anuncioService;

    // Criar um novo anúncio
    @PostMapping
    public ResponseEntity<VendaDTO> criarAnuncio(@RequestBody VendaDTO dto) {
        VendaDTO novoAnuncio = anuncioService.criarAnuncio(dto);
        return ResponseEntity.ok(novoAnuncio);
    }

    // Listar todos os anúncios
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarAnuncios() {
        List<VendaDTO> anuncios = anuncioService.listarAnuncios();
        return ResponseEntity.ok(anuncios);
    }

    // Buscar um anúncio por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable Long id) {
        Optional<VendaDTO> anuncio = anuncioService.buscarPorId(id);
        return anuncio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um anúncio por ID
    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarAnuncio(@PathVariable Long id, @RequestBody VendaDTO dto) {
        Optional<VendaDTO> anuncioAtualizado = anuncioService.atualizarAnuncio(id, dto);
        return anuncioAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir um anúncio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAnuncio(@PathVariable Long id) {
        boolean excluido = anuncioService.excluirAnuncio(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
	
}
