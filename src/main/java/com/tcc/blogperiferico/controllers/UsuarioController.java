package com.tcc.blogperiferico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarTudo() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioDTO> listar(@PathVariable Long id) {
        return ResponseEntity.ok(service.listar(id));
    }

    @CrossOrigin(origins = "/**")
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO dto) {
        if (dto.getRoles() == null) {
            dto.setRoles(UsuarioRole.ROLE_ADMINISTRADOR);
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

    // Atualizar só a foto do perfil
    @PreAuthorize("hasAnyRole('ROLE_USUARIO', 'ROLE_ADMINISTRADOR')")
    @PatchMapping(value = "/{id}/foto", consumes = {"multipart/form-data"})
    public ResponseEntity<UsuarioDTO> atualizarFoto(
            @PathVariable Long id,
            @RequestPart("file") MultipartFile file) {
        return ResponseEntity.ok(service.atualizarFoto(id, file));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}