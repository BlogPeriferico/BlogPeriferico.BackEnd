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
@Table(name  = "tb_doacoes")
public class Doacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private String telefone;
	private Zona zona;
	
	@Column(columnDefinition = "TEXT")
	private String imagem;
	
    private LocalDateTime dataHoraCriacao = LocalDateTime.now();
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario idUsuario;
	
	//Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	public Usuario getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }
	
	//Constructors
	public Doacao() {
	
	}
	public Doacao(Long id, String titulo, String descricao, String telefone, Zona zona,
			String imagem, LocalDateTime dataHoraCriacao, Usuario idUsuario) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.telefone = telefone;
		this.zona = zona;
		this.imagem = imagem;
		this.dataHoraCriacao = dataHoraCriacao;
		this.idUsuario = idUsuario;
	}

}
