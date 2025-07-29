package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

}
