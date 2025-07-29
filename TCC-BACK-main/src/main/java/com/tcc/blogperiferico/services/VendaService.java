package com.tcc.blogperiferico.services;

import com.tcc.blogperiferico.dto.VendaDTO;
import com.tcc.blogperiferico.entities.Usuario;
import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.repository.UsuarioRepository;
import com.tcc.blogperiferico.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar nova venda
    public VendaDTO criarVenda(VendaDTO dto) {
        System.out.println("VendaDTO recebido no serviço: " + dto);
        // Buscar o usuário pelo idUsuario
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
        System.out.println("Usuário encontrado: " + usuario.getEmail());

        // Criar a entidade Venda
        Venda venda = new Venda();
        venda.setTitulo(dto.getTitulo());
        venda.setDescricao(dto.getDescricao());
        venda.setImagem(dto.getImagem());
        venda.setValor(dto.getValor());
        venda.setTelefone(dto.getTelefone());
        venda.setCpf(dto.getCpf());
        venda.setZona(dto.getZona());
        venda.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
        venda.setIdUsuario(usuario);

        // Salvar no banco
        venda = vendaRepository.save(venda);
        System.out.println("Venda salva: " + venda.getId());

        // Retornar DTO
        return new VendaDTO(venda);
    }

    // Listar todas as vendas
    public List<VendaDTO> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        return vendas.stream()
                .map(VendaDTO::new)
                .collect(Collectors.toList());
    }

    // Buscar venda por ID
    public Optional<VendaDTO> buscarPorId(Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        return venda.map(VendaDTO::new);
    }

    // Atualizar venda
    public Optional<VendaDTO> atualizarVenda(Long id, VendaDTO dto) {
        Optional<Venda> vendaOpt = vendaRepository.findById(id);
        if (vendaOpt.isPresent()) {
            Venda venda = vendaOpt.get();
            // Buscar o usuário pelo idUsuario
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getIdUsuario()));
            venda.setTitulo(dto.getTitulo());
            venda.setDescricao(dto.getDescricao());
            venda.setImagem(dto.getImagem());
            venda.setValor(dto.getValor());
            venda.setTelefone(dto.getTelefone());
            venda.setCpf(dto.getCpf());
            venda.setZona(dto.getZona());
            venda.setDataHoraCriacao(dto.getDataHoraCriacao() != null ? dto.getDataHoraCriacao() : LocalDateTime.now());
            venda.setIdUsuario(usuario);
            vendaRepository.save(venda);
            return Optional.of(new VendaDTO(venda));
        }
        return Optional.empty();
    }

    // Excluir venda
    public boolean excluirVenda(Long id) {
        if (vendaRepository.existsById(id)) {
            vendaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}