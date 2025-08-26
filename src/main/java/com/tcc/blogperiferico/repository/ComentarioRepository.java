package com.tcc.blogperiferico.repository;

import com.tcc.blogperiferico.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByNoticiaId(Long noticiaId);
    List<Comentario> findByVendaId(Long vendaId);
    List<Comentario> findByVagaId(Long vagaId);
    List<Comentario> findByDoacaoId(Long doacaoId);
}