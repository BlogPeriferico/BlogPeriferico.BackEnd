package com.tcc.blogperiferico.dto;

import com.tcc.blogperiferico.entities.Comentario;
import java.time.LocalDateTime;

public class ComentarioDTO {

    private Long id;
    private String texto;
    private LocalDateTime dataHoraCriacao;
    private Long idUsuario;
    private String nomeUsuario;

    private Long idNoticia;
    private Long idVenda;
    private Long idVaga;
    private Long idDoacao;

    public ComentarioDTO() {}

    public ComentarioDTO(Comentario c) {
        this.id = c.getId();
        this.texto = c.getTexto();
        this.dataHoraCriacao = c.getDataHoraCriacao();
        this.idUsuario = c.getUsuario().getId();
        this.nomeUsuario = c.getUsuario().getNome();
        this.idNoticia = c.getNoticia() != null ? c.getNoticia().getId() : null;
        this.idVenda   = c.getVenda()   != null ? c.getVenda().getId()   : null;
        this.idVaga    = c.getVaga()    != null ? c.getVaga().getId()    : null;
        this.idDoacao  = c.getDoacao()  != null ? c.getDoacao().getId()  : null;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public LocalDateTime getDataHoraCriacao() { return dataHoraCriacao; }
    public void setDataHoraCriacao(LocalDateTime dataHoraCriacao) { this.dataHoraCriacao = dataHoraCriacao; }

    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public Long getIdNoticia() { return idNoticia; }
    public void setIdNoticia(Long idNoticia) { this.idNoticia = idNoticia; }

    public Long getIdVenda() { return idVenda; }
    public void setIdVenda(Long idVenda) { this.idVenda = idVenda; }

    public Long getIdVaga() { return idVaga; }
    public void setIdVaga(Long idVaga) { this.idVaga = idVaga; }

    public Long getIdDoacao() { return idDoacao; }
    public void setIdDoacao(Long idDoacao) { this.idDoacao = idDoacao; }
}