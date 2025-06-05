package com.tcc.blogperiferico.dtos;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.entities.Noticia.Zona;

import java.time.LocalDate;

public class VendaDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Double valor;
    private String telefone;
    private String cpf;
    private Zona zona;
    private String imagem;
    private LocalDate horaPostagem;
    private Long usuarioId;

    public VendaDTO() {}

    public VendaDTO(Long id, String titulo, String descricao, Double valor, String telefone,
                    String cpf, Zona zona, String imagem, LocalDate horaPostagem, Long usuarioId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.telefone = telefone;
        this.cpf = cpf;
        this.zona = zona;
        this.imagem = imagem;
        this.horaPostagem = horaPostagem;
        this.usuarioId = usuarioId;
    }

    // Construtor a partir da entidade Venda
    public VendaDTO(Venda venda) {
        this.id = venda.getId();
        this.titulo = venda.getTitulo();
        this.descricao = venda.getDescricao();
        this.valor = venda.getValor();
        this.telefone = venda.getTelefone();
        this.cpf = venda.getCpf();
        this.zona = venda.getZona();
        this.imagem = venda.getImagem();
        this.horaPostagem = venda.getHoraPostagem();
        this.usuarioId = venda.getUsuario() != null ? venda.getUsuario().getId() : null;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
