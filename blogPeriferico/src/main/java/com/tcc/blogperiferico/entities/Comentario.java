package com.tcc.blogperiferico.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private String categoria;

    private LocalDate horaPostagem;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "noticia_id")
    private Noticia noticia;

    // Construtor padrão
    public Comentario() {}

    // Construtor completo
    public Comentario(Long id, String texto, String categoria, LocalDate horaPostagem, Usuario usuario, Noticia noticia) {
        this.id = id;
        this.texto = texto;
        this.categoria = categoria;
        this.horaPostagem = horaPostagem;
        this.usuario = usuario;
        this.noticia = noticia;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }
}
