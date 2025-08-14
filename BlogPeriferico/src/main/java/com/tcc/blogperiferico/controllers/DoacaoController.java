package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.dto.DoacaoDTO;
import com.tcc.blogperiferico.services.DoacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class DoacaoController {

    @Autowired
    private DoacaoService doacaoService;

    // Criar um novo anúncio
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<DoacaoDTO> criarDoacao(@RequestBody DoacaoDTO dto) {
        System.out.println("DoacaoDTO recebido no controller: " + dto);
        DoacaoDTO novoDoacao = doacaoService.criarDoacao(dto);
        return ResponseEntity.ok(novoDoacao);
    }

    // Listar todos os anúncios
    @GetMapping
    public ResponseEntity<List<DoacaoDTO>> listarDoacao() {
        List<DoacaoDTO> doacoes = doacaoService.listarDoacoes();
        return ResponseEntity.ok(doacoes);
    }

    // Buscar um anúncio por ID
    @GetMapping("/{id}")
    public ResponseEntity<DoacaoDTO> buscarPorId(@PathVariable Long id) {
        Optional<DoacaoDTO> doacao = doacaoService.buscarPorId(id);
        return doacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um anúncio por ID
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<DoacaoDTO> atualizarDoacao(@PathVariable Long id, @RequestBody DoacaoDTO dto) {
        Optional<DoacaoDTO> doacaoAtualizado = doacaoService.atualizarDoacao(id, dto);
        return doacaoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Excluir um anúncio por ID
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDoacao(@PathVariable Long id) {
        boolean excluido = doacaoService.excluirDoacao(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}