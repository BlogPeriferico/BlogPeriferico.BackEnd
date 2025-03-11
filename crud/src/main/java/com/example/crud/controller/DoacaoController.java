package com.example.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.modal.Doacao;
import com.example.crud.service.DoacaoService;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    @PostMapping
    public ResponseEntity<Doacao> criarDoacao(@RequestBody Doacao doacao) {
        return ResponseEntity.ok(doacaoService.criarDoacao(doacao));
    }

    @GetMapping
    public ResponseEntity<List<Doacao>> listarTodas() {
        return ResponseEntity.ok(doacaoService.listarTodas());
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<Doacao>> listarPedidos() {
        return ResponseEntity.ok(doacaoService.listarPedidos());
    }

    @GetMapping("/ofertas")
    public ResponseEntity<List<Doacao>> listarOfertas() {
        return ResponseEntity.ok(doacaoService.listarOfertas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doacao> buscarPorId(@PathVariable Long id) {
        Optional<Doacao> doacao = doacaoService.buscarPorId(id);
        return doacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id) {
        doacaoService.excluirDoacao(id);
        return ResponseEntity.noContent().build();
    }
}