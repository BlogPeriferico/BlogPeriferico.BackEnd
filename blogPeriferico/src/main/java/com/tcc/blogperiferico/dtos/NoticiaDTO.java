package com.tcc.blogperiferico.dtos;

import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Noticia.Zona;

import java.time.LocalDate;

public class NoticiaDTO {

    private Long id;
    private String titulo;
    private String texto;
    private String imagem;
    private Zona zona;
    private LocalDate horaPostagem;
    private Usuario usuario;

    public NoticiaDTO() {}

    public NoticiaDTO(Long id, String titulo, String texto, String imagem, Zona zona, LocalDate horaPostagem, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.imagem = imagem;
        this.zona = zona;
        this.horaPostagem = horaPostagem;
        this.usuario = usuario;
    }

    public NoticiaDTO(Noticia noticia) {
        this.id = noticia.getId();
        this.titulo = noticia.getTitulo();
        this.texto = noticia.getTexto();
        this.imagem = noticia.getImagem();
        this.zona = noticia.getZona();
        this.horaPostagem = noticia.getHoraPostagem();
        this.usuario = noticia.getUsuario();
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
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
