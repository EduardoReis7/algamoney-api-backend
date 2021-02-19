package com.er.algamoneyapibackend.controllers;

import com.er.algamoneyapibackend.dto.PessoaDto;
import com.er.algamoneyapibackend.model.Pessoa;
import com.er.algamoneyapibackend.services.PessoaService;
import com.er.algamoneyapibackend.services.common.SanitizeService;
import com.er.algamoneyapibackend.util.PessoaUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;
    private final SanitizeService sanitizeService;

    public PessoaController(PessoaService pessoaService, SanitizeService sanitizeService) {
        this.pessoaService = pessoaService;
        this.sanitizeService = sanitizeService;
    }

    @GetMapping
    public ResponseEntity<?> listarPessoas() {
        List<Pessoa> pessoas = this.pessoaService.listarTodos();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPessoa(@PathVariable("id") Long id) {
        Pessoa pessoa = this.pessoaService.buscarPessoa(id);
        return ResponseEntity.ok(pessoa);
    }


    @PostMapping
    public ResponseEntity<?> novaPessoa(@RequestBody @Valid PessoaDto pessoaDto) {
        Pessoa pessoa = this.pessoaService.novaPessoa(this.sanitizeService.sanitize(pessoaDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(PessoaUtil.pessoaToPessoaDto(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarPessoa(@PathVariable("id") Long id, @RequestBody @Valid PessoaDto pessoaDto) {
        Pessoa pessoaEditada = this.pessoaService.editar(id, this.sanitizeService.sanitize(pessoaDto));
        return ResponseEntity.ok(pessoaEditada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirPessoa(@PathVariable("id") Long id) {
        this.pessoaService.excluirPessoa(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> excluirTodasPessoas() {
        this.pessoaService.excluirTudo();
        return ResponseEntity.noContent().build();
    }
}
