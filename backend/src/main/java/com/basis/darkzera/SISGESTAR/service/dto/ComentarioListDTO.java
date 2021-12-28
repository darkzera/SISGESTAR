package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioListDTO implements Serializable {
    private Long id;
    private String descricao;
    private LocalDateTime data_comentario;
    private Long idUsuario;

}
