package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.entities.Zona;

import jakarta.persistence.Column;

public class VendaDTO {

	private Long id;
	private String titulo;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	@Column(columnDefinition = "TEXT")
	private String imagem;
	private Float valor;
	private String telefone;
	private String cpf;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	public VendaDTO() {
	
	}
	public VendaDTO(Long id, String titulo, String descricao, String imagem, Float valor, String telefone, String cpf,
			Zona zona, LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.imagem = imagem;
		this.valor = valor;
		this.telefone = telefone;
		this.cpf = cpf;
		this.zona = zona;
		this.dataHoraCriacao = dataHoraCriacao;
	}
	public VendaDTO(Venda a) {
		id = a.getId();
		titulo = a.getTitulo();
		descricao = a.getDescricao();
		imagem = a.getImagem();
		valor = a.getValor();
		telefone = a.getTelefone();
		dataHoraCriacao = a.getDataHoraCriacao();
	}
	
}
