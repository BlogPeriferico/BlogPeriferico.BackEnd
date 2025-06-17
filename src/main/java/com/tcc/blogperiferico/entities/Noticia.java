package com.tcc.blogperiferico.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_noticias")
public class Noticia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String local;
	private String titulo;

	@Column(columnDefinition = "TEXT")
	private String texto;

	@Column(columnDefinition = "TEXT")
	private String imagem;

	@Enumerated(EnumType.STRING) // Garantindo que o enum seja armazenado como string
	private Zona zona;

	private LocalDateTime dataHoraCriacao = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario idUsuario;

	// Getters and Setters
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

	public Usuario getIdUsuario() {
		return idUsuario;
	}

	public void setUsuario(Usuario idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDateTime getDataHoraCriacao() {
		return dataHoraCriacao;
	}

	public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
		this.dataHoraCriacao = dataHoraCriacao;
	}

	// Construtores
	public Noticia() {
	}

	public Noticia(Long id, Zona zona, String titulo, String texto, String imagem, LocalDateTime dataHoraCriacao,
			Usuario idUsuario) {
		this.id = id;
		this.zona = zona;
		this.titulo = titulo;
		this.texto = texto;
		this.imagem = imagem;
		this.dataHoraCriacao = dataHoraCriacao; // Hora é gerada automaticamente
		this.idUsuario = idUsuario;
	}

}
