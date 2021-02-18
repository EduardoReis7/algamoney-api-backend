package com.er.algamoneyapibackend.util;

import com.er.algamoneyapibackend.dto.CategoriaDto;
import com.er.algamoneyapibackend.model.Categoria;
import org.springframework.stereotype.Component;

@Component
public class CategoriaUtil {

    public static Categoria categoriaToCategoriaDto(CategoriaDto categoriaDto) {
        return Categoria.builder().id(categoriaDto.getId()).nome(categoriaDto.getNome()).build();
    }
}
