package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.repositories.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doacoes")
@CrossOrigin(origins = "*")
public class DoacaoController {

    @Autowired
    private DoacaoRepository repository;

    @GetMapping
    public List<Doacao> listar() {
        return repository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USUARIO', 'ADMIN')")
    public Doacao criar(@RequestBody Doacao doacao) {
        return repository.save(doacao);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')") // ou permitir se for o autor
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
