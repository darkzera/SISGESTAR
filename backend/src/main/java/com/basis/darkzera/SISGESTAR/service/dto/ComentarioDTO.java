package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioDTO implements Serializable {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime data_comentario;
    private Long idUsuario;
    private Long idTarefa;
}
