package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Long idResponsavel;
    private Long idStatus;
    private List<UsuarioDTO> acompanhadores = new ArrayList<>();
    private List<ComentarioDTO> comentarios = new ArrayList<>();

    public TarefaDTO(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

}
