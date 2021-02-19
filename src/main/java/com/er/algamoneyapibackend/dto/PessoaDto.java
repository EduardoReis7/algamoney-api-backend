package com.er.algamoneyapibackend.dto;

import com.er.algamoneyapibackend.model.Endereco;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private Long id;
    @Size(max = 40, message = "O campo nome suporta apenas 40 caracteres.")
    @NotEmpty(message = "O campo nome não pode ser vazio ou nulo.")
    private String nome;
    @NotEmpty(message = "O campo ativo não pode ser vazio ou nulo.")
    private boolean ativo;
    @NotEmpty(message = "O campo endereco não pode ser vazio ou nulo.")
    private Endereco endereco;
}
