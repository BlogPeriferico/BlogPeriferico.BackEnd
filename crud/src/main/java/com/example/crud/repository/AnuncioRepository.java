package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.modal.Anuncio;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long>{

}
