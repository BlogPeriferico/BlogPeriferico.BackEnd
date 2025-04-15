package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Anuncio;

import jakarta.persistence.Column;

public class AnuncioDTO {

	private Long id;
	private String local;
	@Column(columnDefinition = "TEXT")
	private String texto;
	@Column(columnDefinition = "TEXT")
	private String imagem;
	private Float preco;
	private int telefone;
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
	public Float getPreco() {
		return preco;
	}
	public void setPreco(Float preco) {
		this.preco = preco;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
	public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }
	
	//Constructors
	public AnuncioDTO() {
	
	}
	
	public AnuncioDTO(Long id, String local, String texto, String imagem, Float preco, int telefone, LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.local = local;
		this.texto = texto;
		this.imagem = imagem;
		this.preco = preco;
		this.telefone = telefone;
		this.dataHoraCriacao = dataHoraCriacao;
	}
	
	public AnuncioDTO(Anuncio a) {
		id = a.getId();
		local = a.getLocal();
		texto = a.getTexto();
		imagem = a.getImagem();
		preco = a.getPreco();
		telefone = a.getTelefone();
		dataHoraCriacao = a.getDataHoraCriacao();
	}
	
}
