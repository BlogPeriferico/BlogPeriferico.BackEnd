package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.modal.Doacao;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findByPedido(boolean pedido); // Buscar doações ou pedidos
    List<Doacao> findByUsuarioId(Long usuarioId); // Buscar doações de um usuário
}