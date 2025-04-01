package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Comercio;

public class ComercioDTO {
	
	private Long id;
	private String local;
	private String texto;
	private String imagem;
	private LocalDateTime dataHoraCriacao;
	

	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }
	
	//Constructors
	public ComercioDTO() {
	
	}
	
	public ComercioDTO(Long id, String local, String texto, String imagem, LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.local = local;
		this.texto = texto;
		this.imagem = imagem;
		this.dataHoraCriacao = dataHoraCriacao;
	}
	
	public ComercioDTO(Comercio c) {
		id = c.getId();
		local = c.getLocal();
		texto = c.getTexto();
		imagem = c.getImagem();
		dataHoraCriacao = c.getDataHoraCriacao();
	}
	
}
