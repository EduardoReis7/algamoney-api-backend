package com.er.algamoneyapibackend.model;

import lombok.*;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Money valor;
    private String observacao;
}
