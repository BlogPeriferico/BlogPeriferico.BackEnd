package com.tcc.blogperiferico.repositories;

import com.tcc.blogperiferico.entities.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
    List<Doacao> findByUsuarioId(Long usuarioId);
}
