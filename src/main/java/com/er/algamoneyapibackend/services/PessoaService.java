package com.er.algamoneyapibackend.services;

import com.er.algamoneyapibackend.exceptions.NaoEncontradoException;
import com.er.algamoneyapibackend.exceptions.RequisicaoInvalidaException;
import com.er.algamoneyapibackend.model.Pessoa;
import com.er.algamoneyapibackend.repository.PessoaRepository;
import com.er.algamoneyapibackend.services.common.AbstractService;
import com.er.algamoneyapibackend.util.PessoaUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService extends AbstractService<Pessoa> {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    protected JpaRepository<Pessoa, Long> getRepository() {
        return this.pessoaRepository;
    }

    @Override
    public Pessoa editar(Long id, Pessoa novaPessoa) {
        Pessoa pessoaAntiga = this.buscarPessoa(id);
        PessoaUtil.validaPessoa(novaPessoa);
        Pessoa pessoaAtualizada = PessoaUtil.atualizaPessoa(pessoaAntiga, novaPessoa);
        return this.criar(pessoaAtualizada);
    }

    public Pessoa novaPessoa(Pessoa pessoa) {
        if ((pessoa.getNome().isEmpty()) || (pessoa.getEndereco() == null))
            throw new RequisicaoInvalidaException("Favor preencher os campos obrigatórios.");

        return this.getRepository().save(pessoa);
    }

    public Pessoa buscarPessoa(Long id) {
        Optional<Pessoa> optPessoa = this.buscar(id);
        if (optPessoa.isEmpty())
            throw new NaoEncontradoException("Não foi possível encontrar a pessoa.");
        return optPessoa.get();
    }

    public void excluirPessoa(Long id) {
        this.buscarPessoa(id);
        this.excluir(id);
    }
}
