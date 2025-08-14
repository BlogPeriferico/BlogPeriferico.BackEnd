package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.dto.VagaDTO;
import com.tcc.blogperiferico.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vagas")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    // VISITANTE pode visualizar
    @GetMapping
    public ResponseEntity<List<VagaDTO>> listarVagas() {
        return ResponseEntity.ok(vagaService.listarVagas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaDTO> buscarPorId(@PathVariable Long id) {
        Optional<VagaDTO> vaga = vagaService.buscarPorId(id);
        return vaga.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // USUARIO e ADMINISTRADOR podem criar
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PostMapping
    public ResponseEntity<VagaDTO> criarVaga(@RequestBody VagaDTO dto) {
        System.out.println("VagaDTO recebido no controller: " + dto);
        return ResponseEntity.ok(vagaService.criarVaga(dto));
    }

    // USUARIO pode editar própria vaga, ADMINISTRADOR pode editar qualquer
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @PutMapping("/{id}")
    public ResponseEntity<VagaDTO> atualizarVaga(@PathVariable Long id, @RequestBody VagaDTO dto) {
        Optional<VagaDTO> atualizada = vagaService.atualizarVaga(id, dto);
        return atualizada.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // USUARIO pode excluir própria vaga, ADMINISTRADOR qualquer
    @PreAuthorize("hasAnyRole('USUARIO', 'ADMINISTRADOR')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirVaga(@PathVariable Long id) {
        boolean excluido = vagaService.excluirVaga(id);
        return excluido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}