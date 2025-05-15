package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Doacao;

public class DoacaoDTO {

	private Long id;
	private String local;
	private String texto;
	private String imagem;
	private String tipoItem;
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
	public String getTipoItem() {
		return tipoItem;
	}
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}
	
	public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }
	
	//Constructors
	public DoacaoDTO() {
	
	}
	
	public DoacaoDTO(Long id, String local, String texto, String imagem, String tipoItem, LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.local = local;
		this.texto = texto;
		this.imagem = imagem;
		this.tipoItem = tipoItem;
		this.dataHoraCriacao = dataHoraCriacao;
	}
	
	public DoacaoDTO(Doacao d) {
		id = d.getId();
		local = d.getLocal();
		texto = d.getTexto();
		imagem = d.getImagem();
		tipoItem = d.getTipoItem();
		dataHoraCriacao = d.getDataHoraCriacao();
	}
	
}
