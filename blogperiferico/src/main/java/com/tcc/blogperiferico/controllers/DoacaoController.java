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

import com.tcc.blogperiferico.dto.DoacaoDTO;
import com.tcc.blogperiferico.services.DoacaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Criar um novo anúncio
    @PostMapping
    public ResponseEntity<DoacaoDTO> criarDoacao(@RequestBody DoacaoDTO dto) {
    	DoacaoDTO novoDoacao = doacaoService.criarDoacao(dto);
        return ResponseEntity.ok(novoDoacao);
    }
    


    // Listar todas as doacoes
    @GetMapping
    public ResponseEntity<List<DoacaoDTO>> listarDoacao() {
        List<DoacaoDTO> doacoes = doacaoService.listarDoacoes();
        return ResponseEntity.ok(doacoes);
    }

    // Buscar um doacao por ID
    @GetMapping("/{id}")
    public ResponseEntity<DoacaoDTO> buscarPorId(@PathVariable Long id) {
        Optional<DoacaoDTO> doacao = doacaoService.buscarPorId(id);
        return doacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @CrossOrigin(origins = " http://127.0.0.1:5500") 
    @PostMapping("/salvar")
    public ResponseEntity<DoacaoDTO> salvar(@Valid @RequestBody DoacaoDTO doacao) {
        return ResponseEntity.ok(doacaoService.criarDoacao(doacao));
    }

    // Atualizar um doacao por ID
    @PutMapping("/{id}")
    public ResponseEntity<DoacaoDTO> atualizarDoacao(@PathVariable Long id, @RequestBody DoacaoDTO dto) {
        Optional<DoacaoDTO> doacaoAtualizado = doacaoService.atualizarDoacao(id, dto);
        return doacaoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir um doacao por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id) {
        boolean excluido = doacaoService.excluirDoacao(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
