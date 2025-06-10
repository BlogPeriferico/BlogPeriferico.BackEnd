package com.tcc.blogperiferico.dto;

import com.tcc.blogperiferico.entities.Noticia;
import com.tcc.blogperiferico.entities.Zona;

public class NoticiaDTO {

    private Long id;
    private String zona;
    private String titulo;
    private String texto;
    private String imagem;
    private String dataHoraCriacao;

    // Getters and Setters
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDataHoraCriacao() {
        return dataHoraCriacao;
    }

    public void setDataHoraCriacao(String dataHoraCriacao) {
        this.dataHoraCriacao = dataHoraCriacao;
    }

    // Construtores
    public NoticiaDTO() {
    }

    public NoticiaDTO(Long id, String zona, String titulo, String texto, String imagem, String dataHoraCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.zona = zona;
        this.imagem = imagem;
        this.dataHoraCriacao = dataHoraCriacao;
    }

    // Construtor para converter de Noticia para NoticiaDTO
    public NoticiaDTO(Noticia noticia) {
        if (noticia != null) {
            this.id = noticia.getIdNoticia();
            this.titulo = noticia.getTitulo();
            this.texto = noticia.getTexto();
            this.imagem = noticia.getImagemBase64();
            this.dataHoraCriacao = noticia.getHoraPostagem().toString(); // Converte LocalDateTime para String
            this.zona = noticia.getZona() != null ? noticia.getZona().name() : null;  // Convertendo o enum para string
        }
    }

    // Método para converter zona de string para enum
    public Zona getZonaEnum() {
        return this.zona != null ? Zona.valueOf(this.zona) : null;  // Convertendo a string para o enum Zona
    }

    // Método para setter para o enum Zona
    public void setZonaEnum(Zona zona) {
        this.zona = zona != null ? zona.name() : null; // Convertendo o enum para string
    }
}
