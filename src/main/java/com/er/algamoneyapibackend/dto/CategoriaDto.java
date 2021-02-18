package com.er.algamoneyapibackend.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaDto {

    private Long id;

    @Size(max = 50, message = "O campo nome suporta apenas 50 caracteres.")
    @NotEmpty(message = "O campo nome não pode ser vazio ou nulo.")
    private String nome;
}
