package com.er.algamoneyapibackend.services.common;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class AbstractService<T> {

    protected abstract JpaRepository<T, Long> getRepository();

    public T criar(T entity) {
        return this.getRepository().save(entity);
    }

    public Optional<T> buscar(Long id) {
        return this.getRepository().findById(id);
    }

    public List<T> listarTodos() {
        return this.getRepository().findAll();
    }

    public Page<T> listarPaginado(Pageable pageable) {
        return this.getRepository().findAll(pageable);
    }

    public abstract T editar(Long id, T entity);

    public void excluir(Long id) {
        this.getRepository().deleteById(id);
    }

    public void excluirTudo() {
        this.getRepository().deleteAll();
    }
}
