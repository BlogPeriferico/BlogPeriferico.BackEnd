package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Comercio;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {

}
