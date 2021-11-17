package com.basis.darkzera.SISGESTAR.service.dto;

import com.basis.darkzera.SISGESTAR.domain.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String hash;
    private List<ComentarioListDTO> comentarios = new ArrayList<>();
}
