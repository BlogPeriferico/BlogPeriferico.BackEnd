package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
