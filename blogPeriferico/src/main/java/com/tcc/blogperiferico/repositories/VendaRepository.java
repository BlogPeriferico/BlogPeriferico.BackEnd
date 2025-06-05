package com.tcc.blogperiferico.repositories;

import com.tcc.blogperiferico.entities.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByUsuarioId(Long usuarioId);
}
