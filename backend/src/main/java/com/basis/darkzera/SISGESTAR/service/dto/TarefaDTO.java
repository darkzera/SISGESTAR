package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TarefaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long idResponsavel;
    private Long idStatus;
    private List<UsuarioDTO> acompanhadores = new ArrayList<>();

}
