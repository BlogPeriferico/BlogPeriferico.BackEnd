package com.tcc.blogperiferico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.blogperiferico.services.RelatoriosService;

import net.sf.jasperreports.engine.JRException;

@RequestMapping("relatorios")
@RestController
public class RelatoriosController {

	@Autowired
	RelatoriosService relatoriosService;
	
	@GetMapping
	public ResponseEntity<String> gerarRelatoriosPDF(@RequestParam String caminho) throws JRException{
		relatoriosService.gerarRelatorioDoacao(caminho);
		
		return ResponseEntity.ok("Relatorio gerado com sucesso" + caminho);
	}
	
}
