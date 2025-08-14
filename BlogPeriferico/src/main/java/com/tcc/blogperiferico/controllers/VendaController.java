package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.dto.VendaDTO;
import com.tcc.blogperiferico.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    // VISITANTE pode visualizar
    @GetMapping
    public ResponseEntity<List<VendaDTO>> listarVendas() {
        return ResponseEntity.ok(vendaService.listarVendas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> buscarPorId(@PathVariable Long id) {
        Optional<VendaDTO> venda = vendaService.buscarPorId(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // USUARIO e ADMINISTRADOR podem criar
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<VendaDTO> criarVenda(@RequestBody VendaDTO dto) {
        System.out.println("VendaDTO recebido no controller: " + dto);
        return ResponseEntity.ok(vendaService.criarVenda(dto));
    }

    // USUARIO pode editar própria venda, ADMINISTRADOR pode editar qualquer
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<VendaDTO> atualizarVenda(@PathVariable Long id, @RequestBody VendaDTO dto) {
        Optional<VendaDTO> atualizada = vendaService.atualizarVenda(id, dto);
        return atualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // USUARIO pode excluir própria venda, ADMINISTRADOR qualquer
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVenda(@PathVariable Long id) {
        boolean excluido = vendaService.excluirVenda(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}