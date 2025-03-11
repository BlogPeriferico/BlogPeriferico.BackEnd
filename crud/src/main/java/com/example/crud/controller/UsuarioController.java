package com.example.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crud.modal.Usuario;
import com.example.crud.service.UsuarioService;

@Controller
	@RequestMapping("/api/usuarios")
	public class UsuarioController {
		 @Autowired
		    private UsuarioService usuarioService;

		    // Criar Usuário
		    @PostMapping
		    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		        Usuario novoUsuario = usuarioService.salvarUsuario(usuario);
		        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
		    }

		    // Listar Usuários
		    @GetMapping
		    public List<Usuario> listarUsuarios() {
		        return usuarioService.listarUsuarios();
		    }

		    // Obter Usuário por ID
		    @GetMapping("/{id}")
		    public ResponseEntity<Usuario> obterUsuario(@PathVariable Long id) {
		        Optional<Usuario> usuario = usuarioService.obterUsuario(id);
		        if (usuario.isPresent()) {
		            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }

		    // Atualizar Usuário
		    @PutMapping("/{id}")
		    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
		        Optional<Usuario> usuarioExistente = usuarioService.obterUsuario(id);
		        if (usuarioExistente.isPresent()) {
		            usuario.setId(id);
		            Usuario usuarioAtualizado = usuarioService.salvarUsuario(usuario);
		            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
		        } else {
		            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		        }
		    }

		    // Deletar Usuário
		    @DeleteMapping("/{id}")
		    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		        usuarioService.deletarUsuario(id);
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
	}

