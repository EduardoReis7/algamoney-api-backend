package com.er.algamoneyapibackend.controllers;

import com.er.algamoneyapibackend.dto.CategoriaDto;
import com.er.algamoneyapibackend.model.Categoria;
import com.er.algamoneyapibackend.services.CategoriaService;
import com.er.algamoneyapibackend.services.common.SanitizeService;
import com.er.algamoneyapibackend.util.CategoriaUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final SanitizeService sanitizeService;

    public CategoriaController(CategoriaService categoriaService, SanitizeService sanitizeService) {
        this.categoriaService = categoriaService;
        this.sanitizeService = sanitizeService;
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        List<Categoria> categorias = this.categoriaService.listarTodos();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria novaCategoria = this.categoriaService.novaCategoria(this.sanitizeService.sanitize(categoriaDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaUtil.categoriaToCategoriaDto(novaCategoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPeloId(@PathVariable Long id) {
        Categoria categoria = this.categoriaService.buscarCategoria(id);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarCategoria(@PathVariable("id") Long id, @RequestBody @Valid CategoriaDto categoriaDto) {
        Categoria novaCategoria = this.categoriaService.editar(id, this.sanitizeService.sanitize(categoriaDto));
        return ResponseEntity.ok(novaCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCategoria(@PathVariable("id") Long id) {
        this.categoriaService.excluirCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> excluirTodasCategorias() {
        this.categoriaService.excluirTudo();
        return ResponseEntity.noContent().build();
    }
}
