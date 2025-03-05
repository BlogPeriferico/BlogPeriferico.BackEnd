package com.example.blogPeriferia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogPerifericoController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello, World!";
	}

	@GetMapping("/verificar")
	public String verificarBancoDeDados() {
		try {
			jdbcTemplate.execute("SELECT 1");
			return "Conexão com o banco de dados bem-sucedida!";
		} catch (Exception e) {
			return "Falha ao conectar com o banco de dados: " + e.getMessage();
		}
	}
}
