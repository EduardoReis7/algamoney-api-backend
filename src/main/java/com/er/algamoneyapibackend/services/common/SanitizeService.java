package com.er.algamoneyapibackend.services.common;

import com.er.algamoneyapibackend.dto.CategoriaDto;
import com.er.algamoneyapibackend.dto.PessoaDto;
import com.er.algamoneyapibackend.model.Categoria;
import com.er.algamoneyapibackend.model.Pessoa;
import org.owasp.html.PolicyFactory;
import org.springframework.stereotype.Service;

@Service
public class SanitizeService {

    private final PolicyFactory policyFactory;

    public SanitizeService(PolicyFactory policyFactory) {
        this.policyFactory = policyFactory;
    }

    public Categoria sanitize(CategoriaDto dto) {
        return Categoria.builder().nome(policyFactory.sanitize(dto.getNome())).build();
    }

    public Pessoa sanitize(PessoaDto dto) {
        return Pessoa.builder()
                .nome(policyFactory.sanitize(dto.getNome()))
                .ativo(dto.isAtivo())
                .endereco(dto.getEndereco())
                .build();
    }
}
