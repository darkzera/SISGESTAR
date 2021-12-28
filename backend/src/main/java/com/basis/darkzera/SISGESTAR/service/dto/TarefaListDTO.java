package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaListDTO implements Serializable {
    private Long id;
    private String nome;
//    private String idResponsavel;
//    private Long idStatus;

}
