package com.tcc.blogperiferico.entities;

import java.util.ArrayList;
import java.util.List;

import com.tcc.blogperiferico.dto.UsuarioDTO;
import com.tcc.blogperiferico.enums.UsuarioRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 30)
    private UsuarioRole roles;

    @Column(columnDefinition = "TEXT")
    private String fotoPerfil;

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Noticia> noticias = new ArrayList<>();

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doacao> doacoes = new ArrayList<>();

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vaga> vagas = new ArrayList<>();

    @OneToMany(mappedBy = "idUsuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venda> vendas = new ArrayList<>();

    public Usuario() {}

    public Usuario(Long id, String nome, String email, String senha, UsuarioRole roles) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.roles = roles;
    }

    public Usuario(UsuarioDTO udto) {
        this.id = udto.getId();
        this.nome = udto.getNome();
        this.email = udto.getEmail();
        this.senha = udto.getSenha();
        this.roles = udto.getRoles();
        this.fotoPerfil = udto.getFotoPerfil();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public UsuarioRole getRoles() { return roles; }
    public void setRoles(UsuarioRole roles) { this.roles = roles; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public List<Noticia> getNoticias() { return noticias; }
    public void setNoticias(List<Noticia> noticias) { this.noticias = noticias; }

    public List<Doacao> getDoacoes() { return doacoes; }
    public void setDoacoes(List<Doacao> doacoes) { this.doacoes = doacoes; }

    public List<Vaga> getVagas() { return vagas; }
    public void setVagas(List<Vaga> vagas) { this.vagas = vagas; }

    public List<Venda> getVendas() { return vendas; }
    public void setVendas(List<Venda> vendas) { this.vendas = vendas; }
}