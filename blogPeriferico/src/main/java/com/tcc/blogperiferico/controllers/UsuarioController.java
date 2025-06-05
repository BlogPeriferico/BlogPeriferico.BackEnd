package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @GetMapping("/{id}")
    public Optional<Usuario> buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}
