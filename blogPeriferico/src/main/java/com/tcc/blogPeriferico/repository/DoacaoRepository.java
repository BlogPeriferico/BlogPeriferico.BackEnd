package com.tcc.blogPeriferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogPeriferico.entities.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

}
