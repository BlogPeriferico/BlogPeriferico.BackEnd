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
@Table(name  = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;
	
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	@Enumerated(EnumType.STRING)
	private UsuarioRole roles;
	
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> anuncios = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Noticias> noticias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comercio> comercios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Doacao> doacoes;
	
	//Constructors
	public Usuario() {

	}

	public Usuario(Long id, String nome, String email, String senha, String cpf, UsuarioRole roles) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.roles = roles;
	}
	
	public Usuario(UsuarioDTO udto) {
		id = udto.getId();
		nome = udto.getNome();
		email = udto.getEmail();
		senha = udto.getSenha();
		cpf = udto.getCpf();
		roles = udto.getRoles();
	}

	//Getters and Setters
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public UsuarioRole getRoles() {
        return roles;
    }

    public void setRoles(UsuarioRole roles) {
        this.roles = roles;
    }
	
}
