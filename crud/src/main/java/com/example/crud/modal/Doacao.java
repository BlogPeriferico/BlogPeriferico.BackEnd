package com.example.crud.modal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
	@Table(name = "doacoes")
	public class Doacao {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "usuario_id", nullable = false)
	    private Usuario usuario; // Quem está fazendo ou pedindo a doação

	    private String texto; // Descrição da doação
	    private String local; // Local de retirada/entrega
	    private String tipoItem; // Tipo de item (ex: alimentos, roupas, eletrônicos)

	    private String imagemUrl; // URL da imagem do item

	    private boolean pedido; // true = pedido de doação, false = doação oferecida

	    private LocalDateTime dataCriacao = LocalDateTime.now(); // Data da doação

	    public Doacao() {}

	    public Doacao(Usuario usuario, String texto, String local, String tipoItem, String imagemUrl, boolean pedido) {
	        this.usuario = usuario;
	        this.texto = texto;
	        this.local = local;
	        this.tipoItem = tipoItem;
	        this.imagemUrl = imagemUrl;
	        this.pedido = pedido;
	        this.dataCriacao = LocalDateTime.now();
	    }

	    public Long getId() { return id; }
	    public Usuario getUsuario() { return usuario; }
	    public String getTexto() { return texto; }
	    public String getLocal() { return local; }
	    public String getTipoItem() { return tipoItem; }
	    public String getImagemUrl() { return imagemUrl; }
	    public boolean isPedido() { return pedido; }
	    public LocalDateTime getDataCriacao() { return dataCriacao; }

	    public void setTexto(String texto) { this.texto = texto; }
	    public void setLocal(String local) { this.local = local; }
	    public void setTipoItem(String tipoItem) { this.tipoItem = tipoItem; }
	    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
	    public void setPedido(boolean pedido) { this.pedido = pedido; }
	}
