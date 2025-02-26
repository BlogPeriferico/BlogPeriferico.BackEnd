package com.tcc.blogPeriferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogPeriferico.entities.Comercio;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {

}
