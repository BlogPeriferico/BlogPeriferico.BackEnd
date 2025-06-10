package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Vaga;
import com.tcc.blogperiferico.entities.Zona;

public class VagaDTO {
	
	private Long id;
	private String titulo;
	private String descricao;
	private String imagem;
	private Zona zona;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }
	
	//Constructors
	public VagaDTO() {
	
	}
	
	public VagaDTO(Long id, String titulo, String descricao, String imagem,
			Zona zona, LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.imagem = imagem;
		this.zona = zona;
		this.dataHoraCriacao = dataHoraCriacao;
	}
	public VagaDTO(Vaga c) {
		id = c.getId();
		titulo = c.getTitulo();
		descricao = c.getDescricao();
		zona = c.getZona();
		imagem = c.getImagem();
		dataHoraCriacao = c.getDataHoraCriacao();
	}
	
}
