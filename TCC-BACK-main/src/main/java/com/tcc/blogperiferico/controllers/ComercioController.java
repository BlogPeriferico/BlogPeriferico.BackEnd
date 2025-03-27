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

import com.tcc.blogperiferico.dto.ComercioDTO;
import com.tcc.blogperiferico.services.ComercioService;

@RestController
@RequestMapping("/comercios")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    // Criar um novo anúncio
    @PostMapping
    public ResponseEntity<ComercioDTO> criarComercio(@RequestBody ComercioDTO dto) {
    	ComercioDTO novoComercio = comercioService.criarComercio(dto);
        return ResponseEntity.ok(novoComercio);
    }

    // Listar todos os anúncios
    @GetMapping
    public ResponseEntity<List<ComercioDTO>> listarComercio() {
        List<ComercioDTO> comercios= comercioService.listarComercios();
        return ResponseEntity.ok(comercios);
    }

    // Buscar um anúncio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ComercioDTO> buscarPorId(@PathVariable Long id) {
        Optional<ComercioDTO> comercio = comercioService.buscarPorId(id);
        return comercio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um anúncio por ID
    @PutMapping("/{id}")
    public ResponseEntity<ComercioDTO> atualizarComercio(@PathVariable Long id, @RequestBody ComercioDTO dto) {
        Optional<ComercioDTO> comercioAtualizado = comercioService.atualizarComercio(id, dto);
        return comercioAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir um anúncio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirComercio(@PathVariable Long id) {
        boolean excluido = comercioService.excluirComercio(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
