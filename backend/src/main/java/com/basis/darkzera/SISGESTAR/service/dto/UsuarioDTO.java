package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO {
    private Long id;
    private String name;
    private String email;
    private String hash;
}
