package com.basis.darkzera.SISGESTAR.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO implements Serializable {
    private String destinatario;
    private String corpo;
    private String assunto;
}
