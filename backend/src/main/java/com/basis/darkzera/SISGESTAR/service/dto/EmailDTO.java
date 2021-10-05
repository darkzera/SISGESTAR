package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class EmailDTO implements Serializable {
    private String destinatario;
    private String corpo;
    private String assunto;

}
