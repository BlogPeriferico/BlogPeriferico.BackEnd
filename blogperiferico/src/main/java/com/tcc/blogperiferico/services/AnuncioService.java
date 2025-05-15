package com.tcc.blogperiferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.AnuncioDTO;
import com.tcc.blogperiferico.entities.Anuncio;
import com.tcc.blogperiferico.repository.AnuncioRepository;

@Service
public class AnuncioService {


	@Autowired
    private AnuncioRepository anuncioRepository;

    // Criar novo anúncio
    public AnuncioDTO criarAnuncio(AnuncioDTO dto) {
        Anuncio anuncio = new Anuncio(dto.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getPreco(), dto.getTelefone(), dto.getDataHoraCriacao());
        anuncio = anuncioRepository.save(anuncio);
        return new AnuncioDTO(dto.getId(), anuncio.getLocal(), anuncio.getTexto(), anuncio.getImagem(), anuncio.getPreco(), anuncio.getTelefone(), anuncio.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<AnuncioDTO> listarAnuncios() {
        List<Anuncio> anuncios = anuncioRepository.findAll();
        return anuncios.stream()
                .map(a -> new AnuncioDTO(a.getId(), a.getLocal(), a.getTexto(), a.getImagem(), a.getPreco(), a.getTelefone(), a.getDataHoraCriacao()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<AnuncioDTO> buscarPorId(Long id) {
        Optional<Anuncio> anuncio = anuncioRepository.findById(id);
        return anuncio.map(a -> new AnuncioDTO(a.getId(), a.getLocal(), a.getTexto(), a.getImagem(), a.getPreco(), a.getTelefone(), a.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<AnuncioDTO> atualizarAnuncio(Long id, AnuncioDTO dto) {
        Optional<Anuncio> anuncioOpt = anuncioRepository.findById(id);
        if (anuncioOpt.isPresent()) {
            Anuncio anuncio = anuncioOpt.get();
            anuncio.setLocal(dto.getLocal());
            anuncio.setTexto(dto.getTexto());
            anuncio.setImagem(dto.getImagem());
            anuncio.setPreco(dto.getPreco());
            anuncio.setTelefone(dto.getTelefone());
            anuncio.setDataHoraCriacao(dto.getDataHoraCriacao());
            anuncioRepository.save(anuncio);
            return Optional.of(new AnuncioDTO(anuncio.getId(), anuncio.getLocal(), anuncio.getTexto(), anuncio.getImagem(), anuncio.getPreco(), anuncio.getTelefone(), anuncio.getDataHoraCriacao()));
        }
        return Optional.empty();
    }

    // Excluir anúncio
    public boolean excluirAnuncio(Long id) {
        if (anuncioRepository.existsById(id)) {
            anuncioRepository.deleteById(id);
            return true;
        }
        return false;
    }
	
}
