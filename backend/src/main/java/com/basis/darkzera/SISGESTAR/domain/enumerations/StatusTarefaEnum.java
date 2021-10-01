package com.basis.darkzera.SISGESTAR.domain.enumerations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum StatusTarefaEnum {

    A_FAZER(1L, "A FAZER"),
    FAZENDO(2L, "FAZENDO"),
    PAUSADO(3L, "PAUSADO"),
    FEITO(4L, "FEITO");

    private Long id;
    private String descricao;
}
