package com.tcc.blogperiferico.dtos;

import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.entities.Noticia.Zona;

import java.time.LocalDate;

public class DoacaoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String telefone;
    private Zona zona;
    private String imagem;
    private LocalDate horaPostagem;
    private Long usuarioId;

    public DoacaoDTO() {}

    public DoacaoDTO(Long id, String titulo, String descricao, String telefone,
                     Zona zona, String imagem, LocalDate horaPostagem, Long usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.telefone = telefone;
        this.zona = zona;
        this.imagem = imagem;
        this.horaPostagem = horaPostagem;
        this.usuarioId = usuarioId;
    }

    public DoacaoDTO(Doacao doacao) {
        this.id = doacao.getId();
        this.titulo = doacao.getTitulo();
        this.descricao = doacao.getDescricao();
        this.telefone = doacao.getTelefone();
        this.zona = doacao.getZona();
        this.imagem = doacao.getImagem();
        this.horaPostagem = doacao.getHoraPostagem();
        this.usuarioId = doacao.getUsuario() != null ? doacao.getUsuario().getId() : null;
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
