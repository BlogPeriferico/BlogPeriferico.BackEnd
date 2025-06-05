package com.tcc.blogperiferico.controllers;

import com.tcc.blogperiferico.entities.Venda;
import com.tcc.blogperiferico.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    @Autowired
    private VendaService service;

    @GetMapping
    public List<Venda> listar() {
        return service.listar();
    }

    @PostMapping
    public Venda criar(@RequestBody Venda venda) {
        return service.salvar(venda);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
