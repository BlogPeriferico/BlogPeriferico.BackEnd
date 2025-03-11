package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.crud.modal.Comercio;
import com.example.crud.service.ComercioService;

import java.util.List;

@RestController
@RequestMapping("/comercios")
public class ComercioController {

    @Autowired
    private ComercioService comercioService;

    // Método para criar um novo comércio
    @PostMapping
    public Comercio criarComercio(@RequestParam Long userId,
                                   @RequestParam String texto,
                                   @RequestParam String local,
                                   @RequestParam String imagemPromo) {
        return comercioService.criarComercio(userId, texto, local, imagemPromo);
    }

    // Método para listar todos os comércios
    @GetMapping
    public List<Comercio> listarComercios() {
        return comercioService.listarComercios();
    }

    // Método para buscar um comércio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Comercio> buscarComercioPorId(@PathVariable Long id) {
        return comercioService.buscarComercioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Método para atualizar um comércio
    @PutMapping("/{id}")
    public ResponseEntity<Comercio> atualizarComercio(@PathVariable Long id, @RequestBody Comercio comercio) {
        try {
            Comercio comercioAtualizado = comercioService.atualizarComercio(id, comercio);
            return ResponseEntity.ok(comercioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Método para deletar um comércio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComercio(@PathVariable Long id) {
        try {
            comercioService.deletarComercio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}