package com.er.algamoneyapibackend.util;

import com.er.algamoneyapibackend.dto.PessoaDto;
import com.er.algamoneyapibackend.exceptions.RequisicaoInvalidaException;
import com.er.algamoneyapibackend.model.Pessoa;
import org.springframework.stereotype.Component;

@Component
public class PessoaUtil {

    public static PessoaDto pessoaToPessoaDto(Pessoa pessoa) {
        return PessoaDto.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .ativo(pessoa.isAtivo())
                .endereco(pessoa.getEndereco())
                .build();
    }

    public static Pessoa atualizaPessoa(Pessoa pessoaAntiga, Pessoa novaPessoa) {
        pessoaAntiga.setNome(novaPessoa.getNome());
        pessoaAntiga.setAtivo(novaPessoa.isAtivo());
        pessoaAntiga.setEndereco(novaPessoa.getEndereco());

        return pessoaAntiga;
    }

    public static void validaPessoa(Pessoa pessoa) {
        if (pessoa.getNome().isEmpty())
            throw new RequisicaoInvalidaException("Ocorreu um erro ao cadastrar a pessoa.");
        if ((pessoa.getEndereco().getBairro().isEmpty()) ||
                (pessoa.getEndereco().getCep().isEmpty()) ||
                (pessoa.getEndereco().getCidade().isEmpty()) ||
                (pessoa.getEndereco().getLogradouro().isEmpty()) ||
                (pessoa.getEndereco().getNumero().isEmpty()) ||
                (pessoa.getEndereco().getEstado().isEmpty()))
            throw new RequisicaoInvalidaException("Ocorreu um erro ao cadastrar a pessoa.");
    }

}
