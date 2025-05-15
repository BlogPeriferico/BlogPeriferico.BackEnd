package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Noticias;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, Long> {

}
