package com.tcc.blogperiferico.dto;

import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.entities.Zona;
import java.time.LocalDateTime;

public class VendaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String imagem;
    private Float valor;
    private String telefone;
    private String cpf;
    private Zona zona;
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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public Zona getZona() {
        return zona;
    }
    public void setZona(Zona zona) {
        this.zona = zona;
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
    public VendaDTO() {
    }

    public VendaDTO(Long id, String titulo, String descricao, String imagem, Float valor, String telefone, String cpf,
                    Zona zona, LocalDateTime dataHoraCriacao, Long idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.imagem = imagem;
        this.valor = valor;
        this.telefone = telefone;
        this.cpf = cpf;
        this.zona = zona;
        this.dataHoraCriacao = dataHoraCriacao;
        this.idUsuario = idUsuario;
    }

    public VendaDTO(Venda a) {
        this.id = a.getId();
        this.titulo = a.getTitulo();
        this.descricao = a.getDescricao();
        this.imagem = a.getImagem();
        this.valor = a.getValor();
        this.telefone = a.getTelefone();
        this.cpf = a.getCpf();
        this.zona = a.getZona();
        this.dataHoraCriacao = a.getDataHoraCriacao();
        this.idUsuario = a.getIdUsuario() != null ? a.getIdUsuario().getId() : null;
    }

    @Override
    public String toString() {
        return "VendaDTO{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", descricao='" + descricao + '\'' +
               ", imagem='" + imagem + '\'' +
               ", valor=" + valor +
               ", telefone='" + telefone + '\'' +
               ", cpf='" + cpf + '\'' +
               ", zona=" + zona +
               ", dataHoraCriacao=" + dataHoraCriacao +
               ", idUsuario=" + idUsuario +
               '}';
    }
}