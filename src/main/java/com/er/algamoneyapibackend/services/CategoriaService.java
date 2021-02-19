package com.er.algamoneyapibackend.services;

import com.er.algamoneyapibackend.exceptions.NaoEncontradoException;
import com.er.algamoneyapibackend.exceptions.RequisicaoInvalidaException;
import com.er.algamoneyapibackend.model.Categoria;
import com.er.algamoneyapibackend.repository.CategoriaRepository;
import com.er.algamoneyapibackend.services.common.AbstractService;
import com.er.algamoneyapibackend.util.CategoriaUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService extends AbstractService<Categoria> {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    protected JpaRepository<Categoria, Long> getRepository() {
        return this.categoriaRepository;
    }

    public Categoria buscarCategoria(Long id) {
        Optional<Categoria> optCategoria = this.getRepository().findById(id);
        if (optCategoria.isEmpty())
            throw new NaoEncontradoException("Não foi possível encontrar a categoria.");

        return optCategoria.get();
    }

    @Override
    public Categoria editar(Long id, Categoria novaCategoria) {
        Categoria categoriaAtual = this.buscarCategoria(id);
        CategoriaUtil.validaCategoria(novaCategoria);
        Categoria categoriaAtualizada = CategoriaUtil.atualizaCategoria(categoriaAtual, novaCategoria);
        return this.criar(categoriaAtualizada);
    }

    public Categoria novaCategoria(Categoria categoria) {
        if (categoria.getNome().isEmpty())
            throw new RequisicaoInvalidaException("Favor preencher todos os campos obrigatórios.");
        return this.getRepository().save(categoria);
    }
    
    public void excluirCategoria(Long id) {
        this.buscarCategoria(id);
        this.excluir(id);
    }
}
