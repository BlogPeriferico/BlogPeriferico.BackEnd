package com.tcc.blogperiferico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.UsuarioDTO;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.enums.UsuarioRole;
import com.tcc.blogperiferico.exception.SemResultadosException;
import com.tcc.blogperiferico.exception.UsuarioDuplicadoException;
import com.tcc.blogperiferico.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
    UsuarioRepository repository;

    //Listar uma pessoa
    public UsuarioDTO listar(Long id) {
        return new UsuarioDTO(repository.findById(id)
                .orElseThrow(() -> new SemResultadosException()));
    }

    //Listar todas as pessoas
    public List<UsuarioDTO> listar() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }
        //Cadastrar pessoa=-
        public UsuarioDTO salvar (UsuarioDTO user){
            //validação de duplicidade
            if (repository.existsByEmail(user.getEmail())) {
                throw new UsuarioDuplicadoException();
            }
            Usuario usuario = new Usuario(user);
            usuario.setRoles(UsuarioRole.VISITANTE);
            return new UsuarioDTO(repository.save(usuario));
        }

        //Atualizar pessoa
        public UsuarioDTO atualizartudo (UsuarioDTO user, Long id){
            //verificando existencia no banco
            Usuario usuario = repository.findById(id).orElseThrow(() -> new SemResultadosException("atualização."));

            if (repository.existsByEmail(user.getEmail(), id)) {
                throw new UsuarioDuplicadoException();
            }
            usuario.setNome(user.getNome());
            usuario.setEmail(user.getEmail());
            usuario.setSenha(user.getSenha());
            usuario.setCpf(user.getCpf());
            usuario.setRoles(user.getRoles());
            return new UsuarioDTO(repository.save(usuario));
        }

        public UsuarioDTO atualizar (UsuarioDTO user, Long id){
            Usuario usuario = repository.findById(id).orElseThrow(() -> new SemResultadosException("atualização."));

            if (!user.getNome().equals(usuario.getNome())) {
                usuario.setNome(user.getNome());
            }

            if (!user.getEmail().equals(usuario.getEmail())) {
                usuario.setEmail(user.getEmail());
            }

            if (!user.getSenha().equals(usuario.getSenha())) {
                usuario.setSenha(user.getSenha());
            }

            if (!user.getRoles().equals(usuario.getRoles())) {
                usuario.setRoles(user.getRoles());
            }
            return new UsuarioDTO(repository.save(usuario));
        }

        //Deletar pessoa
        public void deletar (Long id){
            if (!repository.existsById(id)) {
                throw new SemResultadosException();
            }
            repository.deleteById(id);
        }
}
