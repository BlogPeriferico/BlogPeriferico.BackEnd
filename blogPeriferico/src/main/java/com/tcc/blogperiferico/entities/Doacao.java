package com.tcc.blogperiferico.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_doacoes")
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Noticia.Zona zona;

    @Lob
    private String imagem;

    private LocalDate horaPostagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtor padrão
    public Doacao() {}

    // Construtor completo
    public Doacao(Long id, String titulo, String descricao, String telefone, Noticia.Zona zona,
                  String imagem, LocalDate horaPostagem, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.telefone = telefone;
        this.zona = zona;
        this.imagem = imagem;
        this.horaPostagem = horaPostagem;
        this.usuario = usuario;
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

    public Noticia.Zona getZona() {
        return zona;
    }

    public void setZona(Noticia.Zona zona) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
