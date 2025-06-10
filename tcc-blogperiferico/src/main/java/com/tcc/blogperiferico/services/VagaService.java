package com.tcc.blogperiferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.VendaDTO;
import com.tcc.blogperiferico.entities.Vaga;
import com.tcc.blogperiferico.repository.VagaRepository;

@Service
public class VagaService {

	@Autowired
    private VagaRepository comercioRepository;

    // Criar novo anúncio
    public VendaDTO criarComercio(VendaDTO dto) {
    	Vaga comercio = new Vaga(dto.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getDataHoraCriacao());
    	comercio = comercioRepository.save(comercio);
        return new VendaDTO(dto.getId(), comercio.getLocal(), comercio.getTexto(), comercio.getImagem(), comercio.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<VendaDTO> listarComercios() {
        List<Vaga> comercios = comercioRepository.findAll();
        return comercios.stream()
                .map(c -> new VendaDTO(c.getId(), c.getLocal(), c.getTexto(), c.getImagem(), c.getDataHoraCriacao()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<VendaDTO> buscarPorId(Long id) {
        Optional<Vaga> comercio = comercioRepository.findById(id);
        return comercio.map(c -> new VendaDTO(c.getId(), c.getLocal(), c.getTexto(), c.getImagem(), c.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<VendaDTO> atualizarComercio(Long id, VendaDTO dto) {
        Optional<Vaga> comercioOpt = comercioRepository.findById(id);
        if (comercioOpt.isPresent()) {
            Vaga comercio = comercioOpt.get();
            comercio.setLocal(dto.getLocal());
            comercio.setTexto(dto.getTexto());
            comercio.setImagem(dto.getImagem());
            comercio.setDataHoraCriacao(dto.getDataHoraCriacao());
            comercioRepository.save(comercio);
            return Optional.of(new VendaDTO(comercio.getId(), comercio.getLocal(), comercio.getTexto(), comercio.getImagem(), comercio.getDataHoraCriacao()));
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
