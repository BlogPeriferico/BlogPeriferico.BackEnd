package com.tcc.blogPeriferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogPeriferico.entities.Noticias;

@Repository
public interface NoticiasRepository extends JpaRepository<Noticias, Long> {

}
