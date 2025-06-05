package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.repositories.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noticias")
@CrossOrigin(origins = "*")
public class NoticiaController {

    @Autowired
    private NoticiaRepository repository;

    @GetMapping
    public List<Noticia> listar() {
        return repository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USUARIO', 'ADMIN')")
    public Noticia criar(@RequestBody Noticia noticia) {
        return repository.save(noticia);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')") // ou implemente verificação se é o autor
    public Noticia atualizar(@PathVariable Long id, @RequestBody Noticia noticia) {
        noticia.setId(id);
        return repository.save(noticia);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')") // ou implemente verificação se é o autor
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
