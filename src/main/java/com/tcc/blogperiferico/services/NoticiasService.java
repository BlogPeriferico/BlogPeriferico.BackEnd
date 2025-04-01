package com.tcc.blogperiferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.NoticiasDTO;
import com.tcc.blogperiferico.entities.Noticias;
import com.tcc.blogperiferico.repository.NoticiasRepository;

@Service
public class NoticiasService {

	@Autowired
    private NoticiasRepository noticiasRepository;

    // Criar novo anúncio
    public NoticiasDTO criarNoticias(NoticiasDTO dto) {
    	Noticias noticias = new Noticias(dto.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getDataHoraCriacao());
    	noticias = noticiasRepository.save(noticias);
        return new NoticiasDTO(noticias.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<NoticiasDTO> listarNoticias() {
        List<Noticias> noticias = noticiasRepository.findAll();
        return noticias.stream()
                .map(n -> new NoticiasDTO(n.getId(), n.getLocal(), n.getTexto(), n.getImagem(), n.getDataHoraCriacao()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<NoticiasDTO> buscarPorId(Long id) {
        Optional<Noticias> noticias = noticiasRepository.findById(id);
        return noticias.map(n -> new NoticiasDTO(n.getId(), n.getLocal(), n.getTexto(), n.getImagem(), n.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<NoticiasDTO> atualizarNoticias(Long id, NoticiasDTO dto) {
        Optional<Noticias> noticiasOpt = noticiasRepository.findById(id);
        if (noticiasOpt.isPresent()) {
        	Noticias noticias = noticiasOpt.get();
        	noticias.setLocal(dto.getLocal());
        	noticias.setTexto(dto.getTexto());
        	noticias.setImagem(dto.getImagem());
        	noticias.setDataHoraCriacao(dto.getDataHoraCriacao());
        	noticiasRepository.save(noticias);
            return Optional.of(new NoticiasDTO(noticias.getId(), noticias.getLocal(), noticias.getTexto(), noticias.getImagem(), noticias.getDataHoraCriacao()));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirNoticias(Long id) {
        if (noticiasRepository.existsById(id)) {
        	noticiasRepository.deleteById(id);
            return true;
        }
        return false;
    }	
}
