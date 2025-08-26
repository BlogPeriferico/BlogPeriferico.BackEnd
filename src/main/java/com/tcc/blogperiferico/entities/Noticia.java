package com.tcc.blogperiferico.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_noticias")
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String local;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String texto;

    // Aqui vai a URL da imagem armazenada no Azure Blob
    @Column(columnDefinition = "TEXT")
    private String imagem;

    @Enumerated(EnumType.STRING)
    private Zona zona;

    private LocalDateTime dataHoraCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario idUsuario;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }

    public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }

    public Usuario getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Usuario idUsuario) { this.idUsuario = idUsuario; }

    // Construtores
    public Noticia() {
        this.dataHoraCriacao = LocalDateTime.now();
    }

    public Noticia(Long id, String local, Zona zona, String titulo, String texto, String imagem,
                   LocalDateTime dataHoraCriacao, Usuario idUsuario) {
        this.id = id;
        this.local = local;
        this.zona = zona;
        this.titulo = titulo;
        this.texto = texto;
        this.imagem = imagem;
        this.dataHoraCriacao = (dataHoraCriacao != null) ? dataHoraCriacao : LocalDateTime.now();
        this.idUsuario = idUsuario;
    }
}
