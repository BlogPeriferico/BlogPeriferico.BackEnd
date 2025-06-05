package com.tcc.blogperiferico.repositories;

import com.tcc.blogperiferico.entities.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    List<Noticia> findByUsuarioId(Long usuarioId);
}
