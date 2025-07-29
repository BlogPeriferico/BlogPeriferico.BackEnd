package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
