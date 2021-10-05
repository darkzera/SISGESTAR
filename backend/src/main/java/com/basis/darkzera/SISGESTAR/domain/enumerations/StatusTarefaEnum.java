package com.basis.darkzera.SISGESTAR.domain.enumerations;

import com.basis.darkzera.SISGESTAR.service.error.StatusInexistenceException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusTarefaEnum {

    A_FAZER(1L, "A FAZER"),
    FAZENDO(2L, "FAZENDO"),
    PAUSADO(3L, "PAUSADO"),
    FEITO(4L, "FEITO");

    private Long id;
    private String descricao;

    public static StatusTarefaEnum obterPorId(Long id){
        return Arrays.stream(StatusTarefaEnum.values())
                .filter(enumeration -> id.equals(enumeration.getId()))
                .findFirst().orElseThrow(StatusInexistenceException::new);
    }
}
