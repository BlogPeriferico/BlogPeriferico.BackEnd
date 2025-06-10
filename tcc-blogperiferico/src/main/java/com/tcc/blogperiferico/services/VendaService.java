package com.tcc.blogperiferico.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.blogperiferico.dto.VendaDTO;
import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.repository.VendaRepository;

@Service
public class VendaService {


	@Autowired
    private VendaRepository anuncioRepository;

    // Criar novo anúncio
    public VendaDTO criarAnuncio(VendaDTO dto) {
        Venda anuncio = new Venda(dto.getId(), dto.getLocal(), dto.getTexto(), dto.getImagem(), dto.getPreco(), dto.getTelefone(), dto.getDataHoraCriacao());
        anuncio = anuncioRepository.save(anuncio);
        return new VendaDTO(dto.getId(), anuncio.getLocal(), anuncio.getTexto(), anuncio.getImagem(), anuncio.getPreco(), anuncio.getTelefone(), anuncio.getDataHoraCriacao());
    }

    // Listar todos os anúncios
    public List<VendaDTO> listarAnuncios() {
        List<Venda> anuncios = anuncioRepository.findAll();
        return anuncios.stream()
                .map(a -> new VendaDTO(a.getId(), a.getLocal(), a.getTexto(), a.getImagem(), a.getPreco(), a.getTelefone(), a.getDataHoraCriacao()))
                .collect(Collectors.toList());
    }

    // Buscar anúncio por ID
    public Optional<VendaDTO> buscarPorId(Long id) {
        Optional<Venda> anuncio = anuncioRepository.findById(id);
        return anuncio.map(a -> new VendaDTO(a.getId(), a.getLocal(), a.getTexto(), a.getImagem(), a.getPreco(), a.getTelefone(), a.getDataHoraCriacao()));
    }

    // Atualizar anúncio
    public Optional<VendaDTO> atualizarAnuncio(Long id, VendaDTO dto) {
        Optional<Venda> anuncioOpt = anuncioRepository.findById(id);
        if (anuncioOpt.isPresent()) {
            Venda anuncio = anuncioOpt.get();
            anuncio.setLocal(dto.getLocal());
            anuncio.setTexto(dto.getTexto());
            anuncio.setImagem(dto.getImagem());
            anuncio.setPreco(dto.getPreco());
            anuncio.setTelefone(dto.getTelefone());
            anuncio.setDataHoraCriacao(dto.getDataHoraCriacao());
            anuncioRepository.save(anuncio);
            return Optional.of(new VendaDTO(anuncio.getId(), anuncio.getLocal(), anuncio.getTexto(), anuncio.getImagem(), anuncio.getPreco(), anuncio.getTelefone(), anuncio.getDataHoraCriacao()));
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
