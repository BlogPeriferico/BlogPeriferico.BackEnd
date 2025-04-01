package com.tcc.blogperiferico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tcc.blogperiferico.dto.UsuarioDTO;
import com.tcc.blogperiferico.services.UsuarioService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "500", description = "Erro ao listar usuários")
    })
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarTudo() {
        List<UsuarioDTO> usuarios = service.listar();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Lista um usuário específico", description = "Retorna os dados de um usuário com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao buscar o usuário")
    })
    @GetMapping("/listar/{id}")
    public ResponseEntity<UsuarioDTO> listar(@PathVariable Long id) {
        UsuarioDTO usuario = service.listar(id);
        return ResponseEntity.ok(usuario);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @Operation(summary = "Cadastra um novo usuário", description = "Cria um novo usuário no sistema com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso", 
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = UsuarioDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Usuário já cadastrado com o mesmo e-mail")
    })
    @PostMapping("/salvar")
    public ResponseEntity<UsuarioDTO> salvar(@Valid @RequestBody UsuarioDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(user));
    }

    @Operation(summary = "Atualiza todos os dados de um usuário", description = "Atualiza todos os campos do usuário com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "409", description = "Dados duplicados ou inválidos")
    })
    @PutMapping("/atualizartudo/{id}")
    public ResponseEntity<UsuarioDTO> atualizarTudo(@Valid @RequestBody UsuarioDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizartudo(user, id));
    }

    @Operation(summary = "Atualiza dados parcialmente de um usuário", description = "Atualiza dados específicos de um usuário, como nome ou e-mail.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário parcialmente atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "409", description = "Dados duplicados ou inválidos")
    })
    @PatchMapping("/atualizar/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(@Valid @RequestBody UsuarioDTO user, @PathVariable Long id) {
        return ResponseEntity.ok(service.atualizar(user, id));
    }

    @Operation(summary = "Deleta um usuário", description = "Remove um usuário do sistema com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro ao deletar usuário")
    })
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}