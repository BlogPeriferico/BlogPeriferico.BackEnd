package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.entities.Vaga;
import com.tcc.blogperiferico.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@CrossOrigin(origins = "*")
public class VagaController {

    @Autowired
    private VagaService service;

    @GetMapping
    public List<Vaga> listar() {
        return service.listar();
    }

    @PostMapping
    public Vaga criar(@RequestBody Vaga vaga) {
        return service.salvar(vaga);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
