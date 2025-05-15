package com.tcc.blogperiferico.relatorios;

import java.time.format.DateTimeFormatter;

import com.tcc.blogperiferico.entities.Doacao;

public class RelatorioDoacaoDTO {
	
	private Long id;
	private String local;
	private String texto;
	private String tipoItem;
	private String dataHoraCriacao;
	
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
	public String getTipoItem() {
		return tipoItem;
	}
	public void setTipoItem(String tipoItem) {
		this.tipoItem = tipoItem;
	}
	public String getDataHoraCriacao() { 
		return dataHoraCriacao; 
	}
    public void setDataHoraCriacao(String dataHoraCriacao) { 
    	this.dataHoraCriacao = dataHoraCriacao; 
    }
	
	//Constructors
	public RelatorioDoacaoDTO() {
	
	}
	
	public RelatorioDoacaoDTO(Doacao d) {
		this.id = d.getId();
		this.local = d.getLocal();
		this.texto = d.getTexto();
		this.tipoItem = d.getTipoItem();
		this.dataHoraCriacao = d.getDataHoraCriacao().atZone(java.time.ZoneId.systemDefault())
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}

}
