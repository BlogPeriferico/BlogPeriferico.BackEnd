package com.tcc.blogperiferico.dto;

import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.entities.Zona;
import java.time.LocalDateTime;

public class DoacaoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String imagem;
    private Zona zona;
    private String telefone;
    private LocalDateTime dataHoraCriacao;
    private Long idUsuario; // Alterado de Usuario para Long

    // Getters and Setters
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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    // Constructors
    public DoacaoDTO() {
    }

    public DoacaoDTO(Long id, String titulo, String descricao, String imagem, Zona zona, String telefone,
                    LocalDateTime dataHoraCriacao, Long idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.zona = zona;
        this.telefone = telefone;
        this.dataHoraCriacao = dataHoraCriacao;
        this.idUsuario = idUsuario;
    }

    public DoacaoDTO(Doacao d) {
        this.id = d.getId();
        this.titulo = d.getTitulo();
        this.descricao = d.getDescricao();
        this.imagem = d.getImagem();
        this.zona = d.getZona();
        this.telefone = d.getTelefone();
        this.dataHoraCriacao = d.getDataHoraCriacao();
        this.idUsuario = d.getIdUsuario() != null ? d.getIdUsuario().getId() : null;
    }

    @Override
    public String toString() {
        return "DoacaoDTO{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", descricao='" + descricao + '\'' +
               ", imagem='" + imagem + '\'' +
               ", zona=" + zona +
               ", telefone='" + telefone + '\'' +
               ", dataHoraCriacao=" + dataHoraCriacao +
               ", idUsuario=" + idUsuario +
               '}';
    }
}