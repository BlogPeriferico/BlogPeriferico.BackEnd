package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}
