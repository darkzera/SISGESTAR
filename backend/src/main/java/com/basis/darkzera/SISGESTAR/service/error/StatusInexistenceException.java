package com.basis.darkzera.SISGESTAR.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        reason = "O status em questao nao existe"
)
public class StatusInexistenceException extends RuntimeException{
}
