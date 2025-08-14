package com.tcc.blogperiferico.dto;

import java.time.LocalDateTime;

import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.entities.Zona;

public class NoticiaDTO {

    private Long id;
    private String local; // Adicionado para corresponder à entidade Noticia
    private Zona zona;
    private String titulo;
    private String texto;
    private String imagem;
    private LocalDateTime dataHoraCriacao;
    private Long idUsuario; // Alterado de Usuario para Long

    // Getters e Setters
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    // Construtores
    public NoticiaDTO() {
    }

    public NoticiaDTO(Long id, String local, Zona zona, String titulo, String texto, String imagem, LocalDateTime dataHoraCriacao, Long idUsuario) {
        this.id = id;
        this.local = local;
        this.zona = zona;
        this.titulo = titulo;
        this.texto = texto;
        this.imagem = imagem;
        this.dataHoraCriacao = dataHoraCriacao;
        this.idUsuario = idUsuario;
    }

    // Construtor para converter de Noticia para NoticiaDTO
    public NoticiaDTO(Noticia noticia) {
        this.id = noticia.getId();
        this.local = noticia.getLocal();
        this.zona = noticia.getZona();
        this.titulo = noticia.getTitulo();
        this.texto = noticia.getTexto();
        this.imagem = noticia.getImagem();
        this.dataHoraCriacao = noticia.getDataHoraCriacao();
        this.idUsuario = noticia.getIdUsuario() != null ? noticia.getIdUsuario().getId() : null;
    }
}