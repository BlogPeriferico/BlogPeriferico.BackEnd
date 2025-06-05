package com.tcc.blogperiferico.dtos;

import com.tcc.blogperiferico.entities.Comentario;

import java.time.LocalDate;

public class ComentarioDTO {

    private Long id;
    private String texto;
    private String categoria;
    private LocalDate horaPostagem;
    private Long usuarioId;

    public ComentarioDTO() {}

    public ComentarioDTO(Long id, String texto, String categoria, LocalDate horaPostagem, Long usuarioId) {
        this.id = id;
        this.texto = texto;
        this.categoria = categoria;
        this.horaPostagem = horaPostagem;
        this.usuarioId = usuarioId;
    }

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.texto = comentario.getTexto();
        this.categoria = comentario.getCategoria();
        this.horaPostagem = comentario.getHoraPostagem();
        this.usuarioId = comentario.getUsuario() != null ? comentario.getUsuario().getId() : null;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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