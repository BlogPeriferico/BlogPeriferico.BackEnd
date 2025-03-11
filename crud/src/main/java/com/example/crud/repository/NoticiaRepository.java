package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.modal.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    
}