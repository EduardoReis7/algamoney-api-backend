package com.er.algamoneyapibackend.controllers;

import com.er.algamoneyapibackend.dto.CategoriaDto;
import com.er.algamoneyapibackend.model.Categoria;
import com.er.algamoneyapibackend.services.CategoriaService;
import com.er.algamoneyapibackend.util.CategoriaUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Categoria> categorias = this.categoriaService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria novaCategoria = this.categoriaService.criar(CategoriaUtil.categoriaToCategoriaDto(categoriaDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
        Categoria categoria = this.categoriaService.buscarCategoria(id);
        return ResponseEntity.ok(categoria);
    }
}
