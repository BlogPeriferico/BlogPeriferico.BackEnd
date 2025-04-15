package com.tcc.blogperiferico.dto;

import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.enums.UsuarioRole;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UsuarioDTO {

	
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório.")
	private String nome;
	@NotBlank(message = "Email é obrigatório.")
	@Email(message = "Email inválido.")
//	@Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9][A-Za-z0-9._-]{0,62}[A-Za-z0-9]@[A-Za-z0-9]+(-[A-Za-z0-9]+)*(\\.[A-Za-z0-9]+(-[A-Za-z0-9]+)*)*(\\.[A-Za-z]{2,})$\n", message = "Email inválido.")
	@Pattern(
		    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
		    message = "E-mail inválido. Use um formato válido, ex: exemplo@email.com"
		)
	private String email;
	@NotBlank(message = "Senha é obrigatória.")
	private String senha;
	@Pattern(regexp = "^[0-9]{11}$", message = "CPF inválido. Use apenas números: 11 dígitos")
	private String cpf;
	@Column(nullable = false)
	private UsuarioRole roles;
	
	//Constructor
	public UsuarioDTO() {
		
	}
	
	public UsuarioDTO(Long id, String nome, String email, String senha, String cpf, UsuarioRole roles) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.roles = roles;
	}
	
	public UsuarioDTO(Usuario u) {
		id = u.getId();
		nome = u.getNome();
		email = u.getEmail();
		senha = u.getSenha();
		cpf = u.getCpf();
		roles = u.getRoles();
	}
	
	//Getter e Setter
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
