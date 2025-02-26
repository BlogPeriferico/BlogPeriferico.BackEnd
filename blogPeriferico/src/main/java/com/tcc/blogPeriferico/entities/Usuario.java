package com.tcc.blogPeriferico.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
	private Long id;
	
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private String[] roles;
	
	
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

	public Usuario(Long id, String nome, String email, String senha, String cpf, String[] roles) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.roles = roles;
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

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
//	// Método para exibir os papéis do usuário
//    public void mostrarRoles() {
//        for (String role : roles) {
//            System.out.println(role);
//        }
//    }
//
//    public static void main(String[] args) {
//        String[] papeis = {"ADMIN", "USER", "MODERADOR"};
//        Usuario usuario = new Usuario(papeis);
//        usuario.mostrarRoles();
//    }
	
}
