package com.basis.darkzera.SISGESTAR;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Tarefa implements Serializable {

    private Long id;
    private String titulo;
    private String descricao;
}
