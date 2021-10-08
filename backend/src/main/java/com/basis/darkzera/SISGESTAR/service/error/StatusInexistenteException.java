package com.basis.darkzera.SISGESTAR.service.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        value = HttpStatus.BAD_REQUEST,
        // TODO FIXME - trocar msg ++ refatorar nome
        reason = "O status nao existe"
)
public class StatusInexistenceException extends RuntimeException{
}
