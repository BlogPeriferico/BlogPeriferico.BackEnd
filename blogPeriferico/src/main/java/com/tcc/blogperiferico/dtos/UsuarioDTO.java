package com.tcc.blogperiferico.dtos;

import com.tcc.blogperiferico.entities.Usuario;

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String imagem;
    private String papel;

    public UsuarioDTO() {}

    public UsuarioDTO(Long id, String nome, String email, String imagem, String papel) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
        this.papel = papel;
    }

    // Construtor a partir da entidade Usuario
    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.imagem = usuario.getImagem();
        this.papel = usuario.getPapel().name();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}
