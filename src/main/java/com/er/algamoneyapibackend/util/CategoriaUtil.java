package com.er.algamoneyapibackend.util;

import com.er.algamoneyapibackend.dto.CategoriaDto;
import com.er.algamoneyapibackend.exceptions.RequisicaoInvalidaException;
import com.er.algamoneyapibackend.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaUtil {

    public static CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        return CategoriaDto.builder().id(categoria.getId()).nome(categoria.getNome()).build();
    }

    public static Categoria atualizaCategoria(Categoria categoriaAntiga, Categoria novaCategoria) {

        categoriaAntiga.setNome(novaCategoria.getNome());
        return categoriaAntiga;
    }

    public static void validaCategoria(Categoria categoria) {
        if (categoria.getNome().isEmpty())
            throw new RequisicaoInvalidaException("Ocorreu um erro ao cadastrar a categoria.");
    }
}
