package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crud.modal.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
