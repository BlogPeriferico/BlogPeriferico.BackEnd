package com.tcc.blogperiferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.ComercioDTO;
import com.tcc.blogperiferico.entities.Comercio;
import com.tcc.blogperiferico.repository.ComercioRepository;

@Service
public class ComercioService {

	@Autowired
    private ComercioRepository comercioRepository;

    // Criar novo anúncio
    public ComercioDTO criarComercio(ComercioDTO dto) {
    	Comercio comercio = new Comercio(dto.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getDataHoraCriacao());
    	comercio = comercioRepository.save(comercio);
        return new ComercioDTO(dto.getId(), comercio.getLocal(), comercio.getTexto(), comercio.getImagem(), comercio.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<ComercioDTO> listarComercios() {
        List<Comercio> comercios = comercioRepository.findAll();
        return comercios.stream()
                .map(c -> new ComercioDTO(c.getId(), c.getLocal(), c.getTexto(), c.getImagem(), c.getDataHoraCriacao()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<ComercioDTO> buscarPorId(Long id) {
        Optional<Comercio> comercio = comercioRepository.findById(id);
        return comercio.map(c -> new ComercioDTO(c.getId(), c.getLocal(), c.getTexto(), c.getImagem(), c.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<ComercioDTO> atualizarComercio(Long id, ComercioDTO dto) {
        Optional<Comercio> comercioOpt = comercioRepository.findById(id);
        if (comercioOpt.isPresent()) {
            Comercio comercio = comercioOpt.get();
            comercio.setLocal(dto.getLocal());
            comercio.setTexto(dto.getTexto());
            comercio.setImagem(dto.getImagem());
            comercio.setDataHoraCriacao(dto.getDataHoraCriacao());
            comercioRepository.save(comercio);
            return Optional.of(new ComercioDTO(comercio.getId(), comercio.getLocal(), comercio.getTexto(), comercio.getImagem(), comercio.getDataHoraCriacao()));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirComercio(Long id) {
        if (comercioRepository.existsById(id)) {
        	comercioRepository.deleteById(id);
            return true;
        }
        return false;
    }
	
}
