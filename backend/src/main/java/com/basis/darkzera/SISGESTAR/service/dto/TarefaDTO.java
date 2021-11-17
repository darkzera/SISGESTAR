package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TarefaDTO implements Serializable {
    private Long id;
    private String nome;
    private String descricao;
    private Long idResponsavel;
    private Long idStatus;

    private List<UsuarioListDTO> acompanhadores = new ArrayList<>();
    private List<ComentarioListDTO> comentarios = new ArrayList<>();
    private LocalDateTime dataAbertura;
    private LocalDateTime dataFechamento;
}
