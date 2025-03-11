package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.modal.Doacao;
import com.example.crud.repository.DoacaoRepository;

@Service
public class DoacaoService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public Doacao criarDoacao(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }

    public List<Doacao> listarTodas() {
        return doacaoRepository.findAll();
    }

    public List<Doacao> listarPedidos() {
        return doacaoRepository.findByPedido(true);
    }

    public List<Doacao> listarOfertas() {
        return doacaoRepository.findByPedido(false);
    }

    public Optional<Doacao> buscarPorId(Long id) {
        return doacaoRepository.findById(id);
    }

    public void excluirDoacao(Long id) {
        doacaoRepository.deleteById(id);
    }
}