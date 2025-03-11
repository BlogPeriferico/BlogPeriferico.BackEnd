package com.example.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.modal.Comercio;
import com.example.crud.modal.Usuario;
import com.example.crud.repository.ComercioRepository;   
import com.example.crud.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ComercioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ComercioRepository comercioRepository;  

    // Método para criar um novo comércio
    public Comercio criarComercio(Long userId, String texto, String local, String imagemPromo) {
        Usuario usuario = usuarioRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Comercio comercio = new Comercio();
        comercio.setTexto(texto);
        comercio.setLocal(local);
        comercio.setImagemPromo(imagemPromo);  
        comercio.setUsuario(usuario);

        return comercioRepository.save(comercio);   
    }

    // Método para listar todos os comércios
    public List<Comercio> listarComercios() {
        return comercioRepository.findAll();
    }

    // Método para buscar um comércio por ID
    public Optional<Comercio> buscarComercioPorId(Long id) {
        return comercioRepository.findById(id);
    }

    // Método para atualizar um comércio
    public Comercio atualizarComercio(Long id, Comercio comercioAtualizado) {
        Comercio comercioExistente = comercioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comércio não encontrado"));

        comercioExistente.setTexto(comercioAtualizado.getTexto());
        comercioExistente.setLocal(comercioAtualizado.getLocal());
        comercioExistente.setImagemPromo(comercioAtualizado.getImagemPromo());
        // Se necessário, você pode atualizar o usuário também, mas isso depende da lógica do seu negócio.

        return comercioRepository.save(comercioExistente);
    }

    // Método para deletar um comércio
    public void deletarComercio(Long id) {
        if (!comercioRepository.existsById(id)) {
            throw new RuntimeException("Comércio não encontrado");
        }
        comercioRepository.deleteById(id);
    }
}