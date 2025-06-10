package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.entities.Zona;

public class DoacaoDTO {

	private Long id;
	private String titulo;
	private String descricao;
	private String imagem;
	private Zona zona;
	private String telefone;
	private LocalDateTime dataHoraCriacao;
	
	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }
	
	//Constructors
	public DoacaoDTO() {
	
	}	
	public DoacaoDTO(Long id, String titulo, String descricao, String imagem, Zona zona, String telefone,
			LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.imagem = imagem;
		this.zona = zona;
		this.telefone = telefone;
		this.dataHoraCriacao = dataHoraCriacao;
	}
	public DoacaoDTO(Doacao d) {
		id = d.getId();
		zona = d.getZona();
		titulo = d.getTitulo();
		descricao = d.getDescricao();
		imagem = d.getImagem();
		telefone = d.getTelefone();
		dataHoraCriacao = d.getDataHoraCriacao();
	}
	
}
