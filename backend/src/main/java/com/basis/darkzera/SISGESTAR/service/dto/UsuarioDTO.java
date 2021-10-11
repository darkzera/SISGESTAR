package com.basis.darkzera.SISGESTAR.service.dto;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class UsuarioDTO {
    private Long id;
    private String name;
    private String email;
    private String hash;
    private List<Tarefa> comentarios = new ArrayList<>();
}
