package com.example.crud.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Anuncio {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String texto;
	    
	    private String local;
	    
	    private String contato;
	    
	    private Double preco;
	    
	    private String imagemProduto;  

	    private LocalDateTime dataPublicacao;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private Usuario usuario;   

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

		public String getLocal() {
			return local;
		}

		public void setLocal(String local) {
			this.local = local;
		}

		public String getContato() {
			return contato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

		public Double getPreco() {
			return preco;
		}

		public void setPreco(Double preco) {
			this.preco = preco;
		}

		public String getImagemProduto() {
			return imagemProduto;
		}

		public void setImagemProduto(String imagemProduto) {
			this.imagemProduto = imagemProduto;
		}

		public LocalDateTime getDataPublicacao() {
			return dataPublicacao;
		}

		public void setDataPublicacao(LocalDateTime dataPublicacao) {
			this.dataPublicacao = dataPublicacao;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}

}
