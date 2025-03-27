package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

}
