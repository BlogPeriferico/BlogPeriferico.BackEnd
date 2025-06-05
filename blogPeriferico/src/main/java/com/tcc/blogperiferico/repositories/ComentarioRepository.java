package com.tcc.blogperiferico.repositories;

import com.tcc.blogperiferico.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuarioId(Long usuarioId);
}
