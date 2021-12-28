package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UsuarioListDTO implements Serializable {
    private Long id;
    private String name;
}
