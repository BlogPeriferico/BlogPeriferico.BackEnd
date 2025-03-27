package com.tcc.blogperiferico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.blogperiferico.dto.UsuarioDTO;
import com.tcc.blogperiferico.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

	@Autowired
    UsuarioService service;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarTudo() {
            List<UsuarioDTO> usuarios = service.listar();
            return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioDTO> listar(@PathVariable Long id) {
            UsuarioDTO usuario = service.listar(id);
            return ResponseEntity.ok(usuario);
    }

    @CrossOrigin(origins = " http://127.0.0.1:5500") 
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO user) {
        return ResponseEntity.ok(service.salvar(user));
    }

    @PutMapping("/atualizartudo/{id}")
    public ResponseEntity<UsuarioDTO> atualizarTudo(@Valid @RequestBody UsuarioDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizartudo(user, id));
    }

    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> atualizar (@Valid @RequestBody UsuarioDTO user, @PathVariable Long id){
        return ResponseEntity.ok(service.atualizar(user, id));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
