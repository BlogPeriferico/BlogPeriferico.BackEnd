package com.tcc.blogperiferico.repositories;

import com.tcc.blogperiferico.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByUsuarioId(Long usuarioId);
}
