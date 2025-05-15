package com.tcc.blogperiferico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tcc.blogperiferico.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
    // Verificação de registros duplicados ao cadastrar
    boolean existsByEmail(String email);

    // Buscando registros duplicados para put/patch, suporta dados parciais e nulos
    @Query("SELECT COUNT(u) > 0 " +
           "FROM Usuario u " +
           "WHERE u.id <> :id " +
           "AND (:email IS NOT NULL AND u.email = :email)") // Fechamento do parêntese corrigido
    boolean existsByEmail(@Param("email") String email, @Param("id") Long id);

}
