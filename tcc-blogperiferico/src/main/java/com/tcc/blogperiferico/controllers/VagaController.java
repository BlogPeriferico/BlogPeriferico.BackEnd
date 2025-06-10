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
import com.tcc.blogperiferico.services.VagaService;

@RestController
@RequestMapping("/comercios")
public class VagaController {

    @Autowired
    private VagaService comercioService;

    // Criar um novo anúncio
    @PostMapping
    public ResponseEntity<VendaDTO> criarComercio(@RequestBody VendaDTO dto) {
    	VendaDTO novoComercio = comercioService.criarComercio(dto);
        return ResponseEntity.ok(novoComercio);
    }

    // Listar todos os anúncios
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarComercio() {
        List<VendaDTO> comercios= comercioService.listarComercios();
        return ResponseEntity.ok(comercios);
    }

    // Buscar um anúncio por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable Long id) {
        Optional<VendaDTO> comercio = comercioService.buscarPorId(id);
        return comercio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um anúncio por ID
    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarComercio(@PathVariable Long id, @RequestBody VendaDTO dto) {
        Optional<VendaDTO> comercioAtualizado = comercioService.atualizarComercio(id, dto);
        return comercioAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir um anúncio por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirComercio(@PathVariable Long id) {
        boolean excluido = comercioService.excluirComercio(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
