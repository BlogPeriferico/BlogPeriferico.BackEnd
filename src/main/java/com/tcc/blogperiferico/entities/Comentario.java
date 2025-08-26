package com.tcc.blogperiferico.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_comentarios")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String texto;

    private LocalDateTime dataHoraCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Comentário pode estar vinculado a apenas uma destas entidades
    @ManyToOne @JoinColumn(name = "noticia_id")
    private Noticia noticia;

    @ManyToOne @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne @JoinColumn(name = "vaga_id")
    private Vaga vaga;

    @ManyToOne @JoinColumn(name = "doacao_id")
    private Doacao doacao;

    public Comentario() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Noticia getNoticia() { return noticia; }
    public void setNoticia(Noticia noticia) { this.noticia = noticia; }

    public Venda getVenda() { return venda; }
    public void setVenda(Venda venda) { this.venda = venda; }

    public Vaga getVaga() { return vaga; }
    public void setVaga(Vaga vaga) { this.vaga = vaga; }

    public Doacao getDoacao() { return doacao; }
    public void setDoacao(Doacao doacao) { this.doacao = doacao; }
}