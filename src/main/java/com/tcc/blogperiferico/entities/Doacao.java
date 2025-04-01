package com.tcc.blogperiferico.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name  = "tb_doacao")
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String local;
	@Column(columnDefinition = "TEXT")
	private String texto;
	@Column(columnDefinition = "TEXT")
	private String imagem;
	private String tipoItem;
	
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
	
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
	public Doacao() {
	
	}
	
	public Doacao(Long id, String local, String texto, String imagem, String tipoItem, LocalDateTime dataHoraCriacao) {
		this.id = id;
		this.local = local;
		this.texto = texto;
		this.imagem = imagem;
		this.tipoItem = tipoItem;
		this.dataHoraCriacao = LocalDateTime.now();
	}
	
	
	
}
