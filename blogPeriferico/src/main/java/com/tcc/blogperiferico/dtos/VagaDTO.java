package com.tcc.blogperiferico.dtos;

import com.tcc.blogperiferico.entities.Noticia.Zona;
import com.tcc.blogperiferico.entities.Vaga;

import java.time.LocalDate;

public class VagaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Zona zona;
    private String imagem;
    private LocalDate horaPostagem;
    private Long usuarioId;

    public VagaDTO() {}

    public VagaDTO(Long id, String titulo, String descricao, Zona zona,
                   String imagem, LocalDate horaPostagem, Long usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.zona = zona;
        this.imagem = imagem;
        this.horaPostagem = horaPostagem;
        this.usuarioId = usuarioId;
    }

    public VagaDTO(Vaga vaga) {
        this.id = vaga.getId();
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.zona = vaga.getZona();
        this.imagem = vaga.getImagem();
        this.horaPostagem = vaga.getHoraPostagem();
        this.usuarioId = vaga.getUsuario() != null ? vaga.getUsuario().getId() : null;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDate getHoraPostagem() {
        return horaPostagem;
    }

    public void setHoraPostagem(LocalDate horaPostagem) {
        this.horaPostagem = horaPostagem;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
