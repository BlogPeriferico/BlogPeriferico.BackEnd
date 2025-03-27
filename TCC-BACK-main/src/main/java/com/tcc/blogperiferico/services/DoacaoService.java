package com.tcc.blogperiferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.DoacaoDTO;
import com.tcc.blogperiferico.entities.Doacao;
import com.tcc.blogperiferico.repository.DoacaoRepository;

@Service
public class DoacaoService {

	@Autowired
    private DoacaoRepository doacaoRepository;

    // Criar novo anúncio
    public DoacaoDTO criarDoacao(DoacaoDTO dto) {
    	Doacao doacao = new Doacao(dto.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getTipoItem(), dto.getDataHoraCriacao());
    	doacao = doacaoRepository.save(doacao);
        return new DoacaoDTO(doacao.getId(), doacao.getLocal(), doacao.getTexto(), doacao.getImagem(), doacao.getTipoItem(), doacao.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<DoacaoDTO> listarDoacoes() {
        List<Doacao> doacoes = doacaoRepository.findAll();
        return doacoes.stream()
                .map(d -> new DoacaoDTO(d.getId(), d.getLocal(), d.getTexto(), d.getImagem(), d.getTipoItem(), d.getDataHoraCriacao()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<DoacaoDTO> buscarPorId(Long id) {
        Optional<Doacao> doacao = doacaoRepository.findById(id);
        return doacao.map(d -> new DoacaoDTO(d.getId(), d.getLocal(), d.getTexto(), d.getImagem(), d.getTipoItem(), d.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<DoacaoDTO> atualizarDoacao(Long id, DoacaoDTO dto) {
        Optional<Doacao> doacaoOpt = doacaoRepository.findById(id);
        if (doacaoOpt.isPresent()) {
        	Doacao doacao = doacaoOpt.get();
        	doacao.setLocal(dto.getLocal());
        	doacao.setTexto(dto.getTexto());
        	doacao.setImagem(dto.getImagem());
        	doacao.setTipoItem(dto.getTipoItem());
        	doacao.setDataHoraCriacao(dto.getDataHoraCriacao());
        	doacaoRepository.save(doacao);
            return Optional.of(new DoacaoDTO(doacao.getId(), doacao.getLocal(), doacao.getTexto(), doacao.getImagem(), doacao.getTipoItem(), doacao.getDataHoraCriacao()));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirDoacao(Long id) {
        if (doacaoRepository.existsById(id)) {
        	doacaoRepository.deleteById(id);
            return true;
        }
        return false;
    }	
	
}
