package com.tcc.blogperiferico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.tcc.blogperiferico.enums.UsuarioRole;
import com.tcc.blogperiferico.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PreAuthorize("hasRole('ROLE_VISITANTE', 'ROLE_USUARIO','ROLE_ADMINISTRADOR')")
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarTudo() {
        return ResponseEntity.ok(service.listar());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR', 'ROLE_USUARIO')")
    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioDTO> listar(@PathVariable Long id) {
        return ResponseEntity.ok(service.listar(id));
    }

    @CrossOrigin(origins = "/**")
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO dto) {
        // Forçar role para "ROLE_ADMINISTRADOR" temporariamente
        if (dto.getRoles() == null) {
            dto.setRoles(UsuarioRole.ROLE_ADMINISTRADOR); // Fixando a role temporariamente
        }
        return ResponseEntity.ok(service.salvar(dto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMINISTRADOR')")
    @PutMapping("/atualizartudo/{id}")
    public ResponseEntity<UsuarioDTO> atualizarTudo(@Valid @RequestBody UsuarioDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizartudo(user, id));
    }

    @PreAuthorize("hasAnyRole('ROLE_USUARIO', 'ROLE_ADMINISTRADOR')")
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizar(user, id));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
