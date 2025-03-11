package com.example.crud.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Noticia {
	  @Id
	    private Long id;
	    private String nome;
	    private String email;
	    private String senha;
	    private String cpf;
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
		public void setUsuario(Usuario usuario) {
			// TODO Auto-generated method stub
			
		}
		public void setDataPublicacao(LocalDateTime now) {
			// TODO Auto-generated method stub
			
		}
		public void setImagem(String imagem) {
			// TODO Auto-generated method stub
			
		}
		public void setLocal(String local) {
			// TODO Auto-generated method stub
			
		}
		public void setTexto(String texto) {
			// TODO Auto-generated method stub
			
		}
		public String getImagem() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getLocal() {
			// TODO Auto-generated method stub
			return null;
		}
		public String getTexto() {
			// TODO Auto-generated method stub
			return null;
		}
}
